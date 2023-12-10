package com.ChristopherSatyaFredellaBalakosaJBusER.dbjson;

import java.util.HashMap;

/**
 * Serializable Class
 * Class used to serializable Object
 * @author Christopher Satya
 */

public class Serializable implements Comparable<Serializable> {
    public final int id; //The unique identifier (ID) assigned to each instance of a subclass.
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<Class<?>, Integer>(); //A static map that stores the last assigned ID for each class type.

    /**
     * Constructs a `Serializable` object and assigns it a unique ID based on its class type.
     * If an ID has already been assigned to this class type, the new ID will be one greater than the
     * previously assigned ID.
     */
    protected Serializable(){
        Integer counter = mapCounter.get(getClass());
        counter = counter == null ? 0 : counter + 1;
        mapCounter.put(getClass(), counter);
        this.id = counter;
    }
    /**
     * Retrieves the last assigned ID for a specific class type.
     * @param <T>    The class type.
     * @param getter The class for which to retrieve the last assigned ID.
     * @return The last assigned ID for the specified class type, or `null` if not found.
     */
    public static <T> Integer getLastAssignedId(Class<T> getter ){
        return mapCounter.get(getter);
    }
    /**
     * Sets the last assigned ID for a specific class type.
     * @param <T>    The class type.
     * @param setter The class for which to set the last assigned ID.
     * @param number The new last assigned ID for the specified class type.
     * @return The previous last assigned ID for the specified class type, or `null` if not found.
     */
    public static <T> Integer setLastAssignedId(Class<T> setter, int number){
        return mapCounter.put(setter, number);
    }
    /**
     * Compares this `Serializable` object to another `Serializable` object based on their IDs.
     * @param temp The `Serializable` object to compare to.
     * @return A negative integer if this object's ID is less than `temp`'s ID, zero if they are equal,
     *         or a positive integer if this object's ID is greater than `temp`'s ID.
     */
    public int compareTo(Serializable temp){
        return ((Integer)this.id).compareTo(temp.id);
    }
    /**
     * Checks if this `Serializable` object is equal to another `Serializable` object based on their IDs.
     * @param temp The `Serializable` object to compare to.
     * @return `true` if the IDs of both objects are equal, `false` otherwise.
     */
    public boolean equals(Serializable temp){
        return temp.id == this.id;
    }
    /**
     * Checks if this `Serializable` object is equal to another object based on their IDs.
     * @param object The object to compare to.
     * @return `true` if the object is an instance of `Serializable` and their IDs are equal, `false` otherwise.
     */
    public boolean equals(Object object){
        return object instanceof Serializable && ((Serializable) object).id == this.id;
    }

}
