package com.example.springboottest2.utils.summary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Prop {
   public static Prop instance = null;
   public Properties prop = new Properties();

   private Prop() {
      try {
         InputStream ins = new FileInputStream("AC.properties");
         this.prop.load(ins);
      } catch (FileNotFoundException var3) {
         var3.printStackTrace();
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public static Prop getInstance() {
      if (instance == null) {
         Class var0 = Prop.class;
         synchronized(Prop.class) {
            if (instance == null) {
               instance = new Prop();
            }
         }
      }

      return instance;
   }
}
