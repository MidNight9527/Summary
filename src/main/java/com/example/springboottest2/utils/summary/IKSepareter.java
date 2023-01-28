package com.example.springboottest2.utils.summary;


import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

import java.io.*;
import java.util.HashSet;

public class IKSepareter {
   private HashSet<String> stopWords = new HashSet();

   public IKSepareter() {
      try {
         InputStream ins = new FileInputStream("STOPLIST.txt");
         BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
         new String();

         String line;
         while((line = reader.readLine()) != null) {
            this.stopWords.add(line);
         }

         reader.close();
      } catch (FileNotFoundException var4) {
         var4.printStackTrace();
      } catch (IOException var5) {
         var5.printStackTrace();
      }

   }

   public String sepWord(String strIn) {
      StringBuffer mSBuffer = new StringBuffer();
      StringReader input = new StringReader(strIn);
      IKSegmentation mSegman = new IKSegmentation(input, true);

      Lexeme mLex;
      try {
         while((mLex = mSegman.next()) != null) {
            if (!this.stopWords.contains(mLex.getLexemeText())) {
               mSBuffer.append(mLex.getLexemeText() + " ");
            }
         }
      } catch (IOException var7) {
         var7.printStackTrace();
      }

      return mSBuffer.toString();
   }
}
