/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.util.UUID;
import static java.util.UUID.randomUUID;

/**
 *
 * @author Admin
 */
public class Random {

    public static String RandomStrGenerator() {

        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString().replaceAll("_", "");

    }
}
