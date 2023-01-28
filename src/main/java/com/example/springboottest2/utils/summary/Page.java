package com.example.springboottest2.utils.summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Page {
   public String sTitle;
   public String sText;
   public String sAbstract;
   public HashSet<String> sKeywords;
   public ArrayList<Paragraph> paraList;
   public HashMap<String, Float> DF;
   public int senNum = 0;
   private IKSepareter spliter = new IKSepareter();
   private int SINGLE_SEN = 9;
   private int SEN_LENGTH = 6;

   public Page(String str) {
      this.sText = str;
      this.sKeywords = new HashSet();
      this.paraList = new ArrayList();
      this.DF = new HashMap();
      this.SINGLE_SEN = Integer.parseInt(Prop.getInstance().prop.getProperty("SINGLE_SEN", "9"));
      this.SEN_LENGTH = Integer.parseInt(Prop.getInstance().prop.getProperty("SEN_LENGTH", "6"));
   }

   public void buildParagraphList() {
      HashSet<String> senHash = new HashSet();
      String[] paragraphs = this.sText.split("\n");

      for(int p = 0; p < paragraphs.length; ++p) {
         String[] sens = paragraphs[p].split("[。|！|!|？|?]");
         ArrayList<String[]> segSenList = new ArrayList();
         ArrayList<String> senList = new ArrayList();
         Pattern pattern = Pattern.compile("http");

         for(int s = 0; s < sens.length; ++s) {
            Matcher matcher = pattern.matcher(sens[s]);
            if (!matcher.matches()) {
               String wStr = this.spliter.sepWord(sens[s]);
               if (!senHash.contains(wStr)) {
                  senHash.add(wStr);
                  String[] wList = wStr.split(" ");
                  if (sens.length != 1) {
                     if (wList.length >= this.SEN_LENGTH) {
                        segSenList.add(wList);
                        senList.add(sens[s]);
                     }
                  } else if (p == 0 && wList.length > 5 || p > 0 && wList.length >= this.SINGLE_SEN) {
                     segSenList.add(wList);
                     senList.add(sens[s]);
                  }
               }
            }
         }

         Paragraph paragraph = new Paragraph(segSenList, senList);
         this.paraList.add(paragraph);
         this.senNum += senList.size();
      }

   }

   public void keywordAndDF() {
      for(int p = 0; p < this.paraList.size(); ++p) {
         Paragraph para = (Paragraph)this.paraList.get(p);

         for(int s = 0; s < para.segSenList.size(); ++s) {
            String[] sens = (String[])para.segSenList.get(s);
            int w;
            String word;
            if (s == 0 || s == para.segSenList.size() - 1) {
               for(w = 0; w < sens.length; ++w) {
                  word = sens[w];
                  if (word.length() > 1) {
                     this.sKeywords.add(sens[w]);
                  }
               }
            }

            for(w = 0; w < sens.length; ++w) {
               word = sens[w];
               float wDf = 1.0F;
               if (this.DF.containsKey(word)) {
                  wDf = (Float)this.DF.get(word) + 1.0F;
               }

               this.DF.put(word, wDf);
            }
         }
      }

   }

   public class Paragraph {
      public ArrayList<String[]> segSenList;
      public ArrayList<String> senList;
      public ArrayList<Float> senWet;

      public Paragraph(ArrayList<String[]> segSen, ArrayList<String> sen) {
         this.segSenList = segSen;
         this.senList = sen;
         this.senWet = new ArrayList();
      }
   }
}
