package com.example.springboottest2.utils.summary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class LongTextProcessor {
   private float KEY_POWER = 2.0F;
   private int WORD_DIFF = 2;

   public LongTextProcessor() {
      this.KEY_POWER = Float.parseFloat(Prop.getInstance().prop.getProperty("KEY_POWER", "2"));
      this.WORD_DIFF = Integer.parseInt(Prop.getInstance().prop.getProperty("WORD_DIFF", "2"));
   }

   public void senWeight(Page page) {
      for(int p = 0; p < page.paraList.size(); ++p) {
         Page.Paragraph para = (Page.Paragraph)page.paraList.get(p);
         HashSet<String> wSet = new HashSet();

         for(int s = 0; s < para.segSenList.size(); ++s) {
            String[] sens = (String[])para.segSenList.get(s);
            float sWet = 0.0F;

            for(int w = 0; w < sens.length; ++w) {
               String word = sens[w];
               wSet.add(word);
               float wWet = (Float)page.DF.get(word);
               if (page.sKeywords.contains(word)) {
                  wWet *= this.KEY_POWER;
               }

               sWet += wWet;
            }

            float diff = (float)wSet.size() / (float)sens.length;
            sWet = sWet / (float)sens.length * (float)Math.pow((double)diff, (double)this.WORD_DIFF);
            para.senWet.add(sWet);
            wSet.clear();
         }
      }

   }

   public static double similarity(Page page, String sen, ArrayList<SenInfo> sens) {
      double simi = 0.0D;

      for(int n = 0; n < sens.size(); ++n) {
         int same = 0;
         String s2 = (String)((Page.Paragraph)page.paraList.get(((SenInfo)sens.get(n)).paraN)).senList.get(((SenInfo)sens.get(n)).senN);

         for(int i = 0; i < sen.length(); ++i) {
            for(int j = 0; j < s2.length(); ++j) {
               if (sen.charAt(i) == s2.charAt(j)) {
                  ++same;
               }
            }
         }

         double temp = (double)same / (double)((sen.length() + s2.length()) / 2);
         if (temp > simi) {
            simi = temp;
         }
      }

      return simi;
   }

   public void createAbs(Page page, float ABSTRACT_PERCENT, int ABSTRACT_LENGTH_THRESHOLD) {
      StringBuffer absBf = new StringBuffer();
      SenInfo[] infos = new SenInfo[page.senNum];
      int i = 0;

      int p;
      int s;
      for(p = 0; p < page.paraList.size(); ++p) {
         Page.Paragraph para = (Page.Paragraph)page.paraList.get(p);

         for(s = 0; s < para.senWet.size(); ++s) {
            SenInfo info = new SenInfo((Float)para.senWet.get(s), p, s);
            infos[i] = info;
            ++i;
         }
      }

      Arrays.sort(infos);
      p = (int)((float)page.sText.length() * ABSTRACT_PERCENT);
      ArrayList<SenInfo> sens = new ArrayList();
      s = 0;

      SenInfo info;
      String sen;
      //int s;
      for(s = 0; s < page.senNum && s <= p; ++s) {
         info = infos[s];
         sen = (String)((Page.Paragraph)page.paraList.get(info.paraN)).senList.get(info.senN);
         if (similarity(page, sen, sens) <= 0.6D) {
            sens.add(info);
            s += sen.length();
         }
      }

      this.sortByOrder(sens);

      for(s = 0; s < sens.size(); ++s) {
         info = (SenInfo)sens.get(s);
         sen = (String)((Page.Paragraph)page.paraList.get(info.paraN)).senList.get(info.senN);
         absBf.append(sen + "。");
      }

      page.sAbstract = absBf.toString();
   }

   void sortByOrder(ArrayList<SenInfo> sens) {
      int len = sens.size();

      for(int i = 0; i < len - 1; ++i) {
         for(int j = 0; j < len - i - 1; ++j) {
            SenInfo tmp;
            if (((SenInfo)sens.get(j)).paraN == ((SenInfo)sens.get(j + 1)).paraN) {
               if (((SenInfo)sens.get(j)).senN > ((SenInfo)sens.get(j + 1)).senN) {
                  tmp = (SenInfo)sens.get(j);
                  sens.set(j, (SenInfo)sens.get(j + 1));
                  sens.set(j + 1, tmp);
               }
            } else if (((SenInfo)sens.get(j)).paraN > ((SenInfo)sens.get(j + 1)).paraN) {
               tmp = (SenInfo)sens.get(j);
               sens.set(j, (SenInfo)sens.get(j + 1));
               sens.set(j + 1, tmp);
            }
         }
      }

   }

   private class SenInfo implements Comparable {
      public int paraN;
      public int senN;
      public float score;

      public SenInfo(float s, int pn, int sn) {
         this.score = s;
         this.paraN = pn;
         this.senN = sn;
      }

      public int compareTo(Object o) {
         float diff = this.score - ((SenInfo)o).score;
         if (diff < 0.0F) {
            return 1;
         } else {
            return diff > 0.0F ? -1 : 0;
         }
      }
   }
}
