package ChristopherSatyaFredellaBalakosaJBusER;

import java.util.HashMap;

public class Serializable
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    protected Serializable(){
        Class<?> aClass = this.getClass();
        if (!mapCounter.containsKey(aClass)){
            mapCounter.put(aClass, 1);
        }
        id = mapCounter.get(aClass);
        mapCounter.put(aClass, mapCounter.get(aClass)+1);
    }
    public static Integer getLastAssignedId (Class<?> aClass){
        return mapCounter.getOrDefault(aClass,0);
    }
    public static Integer setLastAssignedId(Class<?> aClass, int value){
        return mapCounter.replace(aClass, value);
    }
    public boolean equals(Serializable other){
        return this.id == other.id;
    }
    public int CompareTo(Serializable other){
        return Integer.compare(this.id, other.id);
    }
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Serializable other = (Serializable) obj;
        return this.id == other.id;
    }

}
