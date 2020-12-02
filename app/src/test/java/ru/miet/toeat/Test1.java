package ru.miet.toeat;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.miet.toeat.model.*;


public class Test1 {

    private Meal generate(){
        return new Meal();
    }

    @Test
    public void Test_empty_meal(){
        Meal newMeal = generate();
        assertEquals("noname", newMeal.getName());
    }

    @Test
    public void Test_Set_name() throws Exception{
        Meal newMeal = generate();
        newMeal.setName("1234");
    }
}
