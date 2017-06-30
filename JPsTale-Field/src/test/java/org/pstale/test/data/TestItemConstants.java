package org.pstale.test.data;

import static org.pstale.entity.item.ItemConstant.itemDataBase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.pstale.entity.item.Item;

public class TestItemConstants {

    static Logger log = Logger.getLogger(TestItemConstants.class);

    public static void main(String[] args) throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream("item.sql"));

        String ddl = "create table item(" + "code int primary key," + "name varchar(20)," + "category varchar(5),"
                + "clazz int," + "width int," + "height int," + "folder varchar(9)," + "dorpItem varchar(6),"
                + "modelPosi int," + "sound int," + "weaponClass varchar(10)" + ");";

        String dml = "insert into item value(%d, \"%s\", \"%s\", %d, %d, %d, \"%s\", \"%s\", %d, %d, \"%s\");";

        out.println(ddl);
        int len = itemDataBase.length;
        for (int i = 0; i < len; i++) {
            Item it = itemDataBase[i];
            String sql = String.format(dml, it.code, "", it.category, it.Class, it.width / 22, it.height / 22,
                    it.folder, it.dorpItem, it.SetModelPosi, it.sound, it.WeaponClass);

            out.println(sql);
        }

        out.close();

    }

}
