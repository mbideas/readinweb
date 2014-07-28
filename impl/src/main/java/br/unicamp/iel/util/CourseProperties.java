package br.unicamp.iel.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;

public class CourseProperties {
    private JsonObject courseProperties;

    public CourseProperties(JsonObject courseProperties) {
        this.courseProperties = courseProperties;
    }

    public Long[] getPublishedModulesIds(){
        JsonObject modules = courseProperties.get("modules").asObject();
        ArrayList<Long> ids = new ArrayList<Long>();

        Iterator<Member> it = modules.iterator();
        while(it.hasNext()){
            Member m = it.next();
            JsonObject module = m.getValue().asObject();
            if(module.get("status").asBoolean()){
                ids.add(Long.parseLong(m.getName()));
            }
        }

        return Arrays.copyOf(ids.toArray(), ids.size(), Long[].class);
    }

    public Long[] getPublishedActivitiesIds(Long module){
        JsonObject activities = courseProperties
                .get("modules").asObject()
                .get(Long.toString(module)).asObject()
                .get("activities").asObject();
        ArrayList<Long> ids = new ArrayList<Long>();

        Iterator<Member> it = activities.iterator();
        while(it.hasNext()){
            Member m = it.next();
            JsonObject activity = m.getValue().asObject();
            if(activity.get("status").asBoolean()){
                ids.add(Long.parseLong(m.getName()));
            }
        }

        return Arrays.copyOf(ids.toArray(), ids.size(), Long[].class);
    }

    public void publishModule(Long module){
        JsonObject j_module = courseProperties
                .get("modules").asObject()
                .get(Long.toString(module)).asObject();

        j_module.set("status", true);
    }

    public void publishActivity(Long module, Long activity){
        JsonObject j_activity = courseProperties
                .get("modules").asObject()
                .get(Long.toString(module)).asObject()
                .get("activities").asObject()
                .get(Long.toString(activity)).asObject();

        j_activity.set("status", true);
    }

    public void publishNextActivity(Long currentModule, Long currentActivity){
        // Pega o modulo.
        // Itera no modulo até achar a current.
        // Se tiver próxima, libera
        // Senão, verifica se tem outro modulo
        //   Se tiver, verifica se tem atividade e se sim, libera
        //   Senão, morre (modulo vazio)
        // Se não tiver morre (acabou atividades)
    }
}