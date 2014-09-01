package br.unicamp.iel.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import lombok.Setter;

import org.apache.log4j.Logger;
import org.sakaiproject.genericdao.api.search.Order;
import org.sakaiproject.genericdao.api.search.Restriction;
import org.sakaiproject.genericdao.api.search.Search;
import org.sakaiproject.site.api.Site;
import org.sakaiproject.site.api.SitePage;
import org.sakaiproject.user.api.User;
import org.sakaiproject.user.api.UserNotDefinedException;

import br.unicamp.iel.dao.ReadInWebDao;
import br.unicamp.iel.model.Activity;
import br.unicamp.iel.model.Justification;
import br.unicamp.iel.model.JustificationMessage;
import br.unicamp.iel.model.ReadInWebAnswer;
import br.unicamp.iel.model.Course;
import br.unicamp.iel.model.DictionaryWord;
import br.unicamp.iel.model.Exercise;
import br.unicamp.iel.model.FunctionalWord;
import br.unicamp.iel.model.Module;
import br.unicamp.iel.model.Property;
import br.unicamp.iel.model.Question;
import br.unicamp.iel.model.Strategy;
import br.unicamp.iel.model.sets.ActivitySets;
import br.unicamp.iel.model.sets.CourseSets;
import br.unicamp.iel.model.sets.JustificationSets;
import br.unicamp.iel.model.sets.ModuleSets;
import br.unicamp.iel.util.CourseProperties;
import br.unicamp.iel.util.UserProperties;

import com.eclipsesource.json.JsonObject;

/**
 * Implementation of {@link ReadInWebCommonLogic}
 *
 * @author Virgilio N Santos
 *
 */
public class ReadInWebCommonLogicImpl implements ReadInWebCommonLogic {

    private static final Logger log =
            Logger.getLogger(ReadInWebCommonLogicImpl.class);

    @Setter
    private ReadInWebDao dao;

    @Setter
    private SakaiProxy sakaiProxy;

    public void init() {
        log.info(ReadInWebCommonLogic.class + " init");
    }

    @Override
    public void loadInitialCSVData() {
        // TODO Auto-generated method stub
    }

    @Override
    public Long[] getListIds(List<?> list){
        Long[] result = new Long[list.size()];

        Iterator<?> it = list.iterator();
        int i = 0;
        int size = list.size();
        while(it.hasNext() && i < size){
            Object o = it.next();
            if(o instanceof Activity){
                Activity a = (Activity) o;
                result[i] = (a.getId());
            } else if(o instanceof Module){
                Module m = (Module) o;
                result[i] = (m.getId());
            } else if(o instanceof Question){
                Question q = (Question) o;
                result[i] = (q.getId());
            }
            i++;
        }

        return result;
    }

    @Override
    public String getUserId() {
        return sakaiProxy.getCurrentUserId();
    }

    @Override
    public Course getCourse(Long course) {
        return dao.findById(Course.class, course);
    }

    @Override
    public Module getModule(Long module) {
        return dao.findById(Module.class, module);
    }

    @Override
    public Activity getActivity(Long activity) {
        return dao.findById(Activity.class, activity);
    }

    @Override
    public Question getQuestion(Long question) {
        return dao.findById(Question.class, question);
    }

    @Override
    public Exercise getExercise(Long exercise) {
        return dao.findById(Exercise.class, exercise);
    }

    @Override
    public Strategy getStrategy(Long strategy) {
        return dao.findById(Strategy.class, strategy);
    }

    @Override
    public Justification getJustification(Long justification) {
        return dao.findById(Justification.class, justification);
    }

    @Override
    public ReadInWebAnswer getStudentAnswer(Long question) {
        ReadInWebAnswer answer = dao.findOneBySearch(ReadInWebAnswer.class,
                new Search(new Restriction[]{
                        new Restriction("question.id", question),
                        new Restriction("user", getUserId()),
                }));

        if(answer == null)
            return new ReadInWebAnswer();
        else
            return answer;
    }

    @Override
    public Long countUserAnswers(String user, Long[] ids){
        Restriction[] r = new Restriction[]{
                new Restriction("question.id", ids),
                new Restriction("user", user)
        };
        return dao.countBySearch(ReadInWebAnswer.class, new Search(r));
    }

    @Override
    public List<Course> getCourses() {
        return dao.findAll(Course.class);
    }

    @Override
    public List<Module> getModules(Course course) {
        CourseSets cs = new CourseSets(course);
        return cs.getModules(dao);
    }

    @Override
    public List<Activity> getActivities(Module module) {
        ModuleSets ms = new ModuleSets(module);
        return new ArrayList<Activity>(ms.getActivities(dao));
    }

    @Override
    public List<FunctionalWord> getFunctionalWord (Course course) {
        CourseSets cs = new CourseSets(course);
        return new ArrayList<FunctionalWord>(cs.getFunctionalWords(dao));
    }

    @Override
    public List<DictionaryWord> getDictionary(Activity activity) {
        ActivitySets as = new ActivitySets(activity);
        return new ArrayList<DictionaryWord>(as.getDictionary(dao));
    }

    @Override
    public List<Strategy> getStrategies(Activity activity) {
        ActivitySets as = new ActivitySets(activity);
        return new ArrayList<Strategy>(as.getStrategies(dao));
    }

    public List<Question> getQuestions(Activity activity){
        ActivitySets as = new ActivitySets(activity);
        return new ArrayList<Question>(as.getQuestions(dao));
    }

    @Override
    public List<Exercise> getExercises(Activity activity) {
        ActivitySets as = new ActivitySets(activity);
        return new ArrayList<Exercise>(as.getExercises(dao));
    }

    @Override
    public List<Justification> getUserJustifications(String user, String siteId) {
        Search search = new Search(new Restriction[]{
                new Restriction("user", user),
                new Restriction("site", siteId),
        });
        search.addOrder(new Order("sentDate", false));
        return dao.findBySearch(Justification.class, search);
    }

    @Override
    public List<JustificationMessage> getJustificationMessages(
            Justification justification) {
        JustificationSets js = new JustificationSets(justification);

        return new ArrayList<JustificationMessage>(
                js.getJustificationMessages(dao));
    }

    @Override
    public List<Justification> getSiteJustifications(String siteId) {
        Search search = new Search(new Restriction[]{
                new Restriction("site", siteId),
        });
        search.addOrder(new Order("sentDate"));
        return dao.findBySearch(Justification.class, search);
    }

    @Override
    public String getCurrentSiteId() {
        return sakaiProxy.getCurrentSiteId();
    }

    @Override
    public void saveCourse(Course c) {
        dao.save(c);
    }

    @Override
    public void saveModule(Module m) {
        dao.save(m);
    }

    @Override
    public void saveActivity(Activity a) {
        dao.save(a);
    }

    @Override
    public void saveDictionaryWord(DictionaryWord dw) {
        dao.save(dw);
    }

    @Override
    public void saveExercise(Exercise e) {
        dao.save(e);
    }

    @Override
    public void saveQuestion(Question q) {
        dao.save(q);
    }

    @Override
    public void saveFunctionalWord(FunctionalWord fw) {
        dao.save(fw);
    }

    @Override
    public void saveStrategy(Strategy strategy) {
        dao.save(strategy);
    }

    @Override
    public void saveJustification(Justification j) {
        dao.save(j);
    }

    @Override
    public void saveJustificationMessage(JustificationMessage jm) {
        dao.save(jm);
    }

    @Override
    public void deleteEntity(Object entity){
        dao.delete(entity);
    }

    @Override
    public String getDefaultCoursePropertyString(Course course) {
        JsonObject jo = new JsonObject();
        jo.add("course", course.getId());
        jo.add("modules", new JsonObject());

        List<Module> modules = getModules(course);
        for(Module m : modules){
            JsonObject j_m = new JsonObject();
            j_m.add("status", false);
            j_m.add("activities", new JsonObject());
            jo.get("modules").asObject().add(m.getId().toString(), j_m);

            List<Activity> activities = getActivities(m);
            for(Activity a : activities){
                JsonObject j_a = new JsonObject();
                j_a.add("status", false);
                j_m.get("activities").asObject()
                .add(a.getId().toString(), j_a);
            }
        }
        return jo.toString();
    }

    @Override
    public String getDefaultUserPropertyString() {
        JsonObject jo = new JsonObject();
        jo.add("status", new JsonObject());

        JsonObject status = jo.get("status").asObject();
        status.add("blocked", false);
        status.add("date", (String) null);
        status.add("date_sent", (String) null);
        status.add("blocks", 0);

        return jo.toString();
    }

    @Override
    public String userPropertySkelString(String siteId) {
        JsonObject jo = new JsonObject();
        jo.add("sites", new JsonObject());
        JsonObject sites = jo.get("sites").asObject();
        sites.add(siteId, JsonObject.readFrom(getDefaultUserPropertyString()));

        return jo.toString();
    }

    @Override
    public String getCoursePropertyString(Site site) {
        return sakaiProxy.getStringProperty(site,
                Property.COURSEDATA.getName());
    }

    @Override
    public void setCoursePropertyString(String siteId, String value) {
        Site site = sakaiProxy.getSite(siteId);
        sakaiProxy.setStringProperty(site,
                Property.COURSEDATA.getName(), value);
    }

    @Override
    public String getUserPropertyString(String userId) {
        return sakaiProxy.getJsonUserStringProperty(userId,
                Property.USERDATA.getName());
    }

    @Override
    public void setUserPropertyString(String userId, String value) {
        sakaiProxy.setJsonUserStringProperty(userId,
                Property.USERDATA.getName(), value);
    }

    @Override
    public Long[] getAllPublishedActivities(String siteId) {
        CourseProperties courseProperties =
                new CourseProperties(JsonObject
                        .readFrom(getCoursePropertyString(
                                sakaiProxy.getSite(siteId))));
        return courseProperties.getAllPublishedActivities();
    }


    @Override
    public List<Activity> getPublishedActivities(String siteId, Module module) {
        CourseProperties courseProperties =
                new CourseProperties(JsonObject
                        .readFrom(getCoursePropertyString(
                                sakaiProxy.getSite(siteId))));
        ModuleSets ms = new ModuleSets(module);

        return new ArrayList<Activity>(ms.getPublishedActivies(dao,
                courseProperties.getPublishedActivitiesIds(module.getId())));
    }

    @Override
    public List<Module> getPublishedModules(String siteId, Course course) {
        CourseProperties courseProperties =
                new CourseProperties(JsonObject
                        .readFrom(getCoursePropertyString(
                                sakaiProxy.getSite(siteId))));
        CourseSets cs = new CourseSets(course);

        ArrayList<Module> modules = new ArrayList<Module>(cs.getPublishedModules(dao,
                courseProperties.getPublishedModulesIds()));

        return modules;
    }

    @Override
    public boolean hasSentJustification(User user, Site site) {
        UserProperties userProperties =
                new UserProperties(JsonObject
                        .readFrom(getUserPropertyString(user.getId())));
        return userProperties.hasSentJustification(site.getId());
    }

    @Override
    public void setJustificationSent(String userId, String siteId, Date date) {
        UserProperties userProperties = new UserProperties(JsonObject
                .readFrom(getUserPropertyString(userId)));
        userProperties.setDateSent(siteId, date);
        setUserPropertyString(userId, userProperties.toString());
    }

    @Override
    public boolean isUserBLocked(User user, Site site) {
        String properties = getUserPropertyString(user.getId());
        if(properties == null){
            String value = userPropertySkelString(site.getId());
            setUserPropertyString(user.getId(), value);
            return false;
        } else {
            UserProperties userProperties =
                    new UserProperties(JsonObject
                            .readFrom(properties));
            if(userProperties.hasUserData(site.getId())){
                return userProperties.isUserBlocked(site.getId());
            } else {
                userProperties.addUserData(site.getId(),
                        getDefaultUserPropertyString());

                setUserPropertyString(user.getId(), userProperties.toString());
                return false;
            }
        }
    }

    @Override
    public void blockUser(String siteId, String userId) {
        UserProperties userProperties =
                new UserProperties(JsonObject
                        .readFrom(getUserPropertyString(userId)));

        userProperties.setUserBlocked(siteId, true);
        setUserPropertyString(userId, userProperties.toString());
    }

    // FIXME Move this method to management only. The user will be never
    // unblocked in a course scope, only management
    @Override
    public void unblockUser(String siteId, String userId) {
        UserProperties userProperties =
                new UserProperties(JsonObject
                        .readFrom(getUserPropertyString(userId)));

        userProperties.setUserBlocked(siteId, false);
        setUserPropertyString(userId, userProperties.toString());
    }

    @Override
    public void cleanExpireDate(String siteId, String userId) {
        UserProperties userProperties =
                new UserProperties(JsonObject
                        .readFrom(getUserPropertyString(userId)));

        userProperties.cleanExpireDate(siteId);
        setUserPropertyString(userId, userProperties.toString());
    }

    @Override
    public Long getUserBlockingDate(String siteId, String userId) {
        UserProperties userProperties =
                new UserProperties(JsonObject
                        .readFrom(getUserPropertyString(userId)));

        return userProperties.getBlockingDate(siteId);
    }

    @Override
    public Long getRemissionTime(String siteId) {
        Long weeks = Long.valueOf(sakaiProxy.getStringProperty(
                sakaiProxy.getSite(siteId),
                Property.COURSEREMISSIONTIME.getName()));

        return new Long(weeks*7*24*60*60*1000);
    }

    @Override
    public boolean isActivityPublished(Site riwClass, Long module,
            Long activity) {
        CourseProperties courseProperties =
                new CourseProperties(JsonObject
                        .readFrom(getCoursePropertyString(riwClass)));
        return courseProperties.isActivityPublished(module, activity);
    }

    @Override
    public boolean isModulePublished(Site riwClass, Long module) {
        CourseProperties courseProperties =
                new CourseProperties(JsonObject
                        .readFrom(getCoursePropertyString(riwClass)));
        return courseProperties.isModulePublished(module);
    }

    @Override
    public Integer getUserBlocks(User user, String siteId) {
        // FIXME Pass user, not userId, any entity should be queried just once
        UserProperties userProperties =
                new UserProperties(JsonObject
                        .readFrom(getUserPropertyString(user.getId())));

        return userProperties.getNumBlocks(siteId);
    }

    @Override
    public User getTeacher(String teacherId){
        return sakaiProxy.getUser(teacherId);
    }

    @Override
    public boolean idiomCourseExists(String idiom) {
        return (dao.findBySearch(Course.class,
                new Search("idiom", idiom))).size() > 0;
    }

    @Override
    public void addReadInWebAdmin() {
        sakaiProxy.createReadInWebAdminPage();
    }

    @Override
    public void addReadInWebManager(Course course) {
        sakaiProxy.createManagerPage(course);
    }

    @Override
    public List<Justification> getSiteJustifications(Site currentSite) {
        return dao.findBySearch(Justification.class,
                new Search("site", currentSite.getId()));
    }
}
