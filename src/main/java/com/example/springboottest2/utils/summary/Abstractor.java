package com.example.springboottest2.utils.summary;

public class Abstractor {
   private static Abstractor abstractor;
   private LongTextProcessor longTextProcessor = new LongTextProcessor();
   private float ABSTRACT_PERCENT;
   private int ABSTRACT_LENGTH_THRESHOLD;

   private Abstractor() {
      this.loadConf();
   }

   public static synchronized Abstractor getInstance() {
      if (abstractor == null) {
         abstractor = new Abstractor();
      }

      return abstractor;
   }

   public void loadConf() {
      this.ABSTRACT_PERCENT = Float.parseFloat(Prop.getInstance().prop.getProperty("ABSTRACT_PERCENT"));
      this.ABSTRACT_LENGTH_THRESHOLD = Integer.parseInt(Prop.getInstance().prop.getProperty("ABSTRACT_LENGTH_THRESHOLD"));
   }

   public String getAbstract(Page page, float in_precent) {
      String abstractText;
      if (page.senNum <= 0) {
         abstractText = "本文不适合做摘要处理";
         System.out.println(" 本文不适合做摘要处理 ");
      } else {
         this.longTextProcessor.senWeight(page);
         this.ABSTRACT_PERCENT = in_precent;
         this.longTextProcessor.createAbs(page, in_precent, this.ABSTRACT_LENGTH_THRESHOLD);
         abstractText = page.sAbstract;
      }

      return abstractText;
   }
}
