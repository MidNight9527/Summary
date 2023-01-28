package com.example.springboottest2.utils.summary;

import java.io.*;

public class Summary {
   private String inPath = new String();
   private String outPath = new String();

   private Abstractor abstrator = Abstractor.getInstance();

   public void AbsFromPath() {
      File path = new File(this.inPath);
      File[] files = path.listFiles();
      if (files != null) {
         for(int i = 0; i < files.length; ++i) {
            try {
               FileWriter writer = new FileWriter(this.outPath + "abs_" + files[i].getName());
               String absStr = this.AbsFromFile(files[i].getAbsolutePath(), 0.25F);
               writer.write(absStr);
               writer.close();
            } catch (IOException var6) {
               System.out.println("写入文件异常！");
            }
         }
      }

   }

   public String AbsFromFile(String filePath, float in_precent) {
      StringBuffer fStr = new StringBuffer();

      try {
         BufferedReader mReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));

         String line;
         while((line = mReader.readLine()) != null) {
            fStr.append(line + "\n");
         }

         mReader.close();
      } catch (UnsupportedEncodingException var6) {
         var6.printStackTrace();
      } catch (FileNotFoundException var7) {
         var7.printStackTrace();
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      return this.AbsFromStr(fStr.toString(), in_precent);
   }

   public String AbsFromStr(String srcStr, float in_precent) {
      Page mPage = new Page(srcStr);
      mPage.buildParagraphList();
      mPage.keywordAndDF();
      String absStr = this.abstrator.getAbstract(mPage, in_precent);
      return absStr;
   }

}
