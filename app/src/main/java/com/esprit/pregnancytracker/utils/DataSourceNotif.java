package com.esprit.pregnancytracker.utils;

import com.esprit.pregnancytracker.Models.Notification;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Asus on 04/01/2018.
 */

public class DataSourceNotif {
  public static   List<Notification> notifications = new ArrayList<>();

    public static void setNotifications() {
        notifications.add(new Notification(1,"It's the first week.", "week 1 of pregnancy","week1"));
        notifications.add(new Notification(2,"It's a potentially life-changing week. You'll ovulate, and if egg meets sperm, you'll be on your way to pregnancy!", "week 2 of pregnancy","week2"));
        notifications.add(new Notification(3,"Your baby is a tiny ball called a blastocyst made up of several hundred cells that are multiplying quickly.", "week 3 of pregnancy","week3"));
        notifications.add(new Notification(4,"Deep in your uterus, your baby is an embryo made up of two layers, and your primitive placenta is developing.", "week 4 of pregnancy","week4"));
        notifications.add(new Notification(5,"Your tiny embryo is growing like crazy, and you may be noticing pregnancy discomforts like sore breasts and fatigue.", "week 5 of pregnancy","week5"));
        notifications.add(new Notification(6,"Your baby's nose, mouth, and ears are beginning to take shape. You may be having morning sickness and spotting.", "week 6 of pregnancy","week6"));
        notifications.add(new Notification(7,"Your baby still an embryo with a small tail is forming hands and feet. Your uterus has doubled in size.", "week 7 of pregnancy","week7"));
        notifications.add(new Notification(8,"Your baby is constantly moving, though you can't feel it. Meanwhile, you may be making decisions about prenatal tests.", "week 8 of pregnancy","week8"));
        notifications.add(new Notification(9,"Nearly an inch long now, your baby is starting to look more human. You've probably noticed your waist thickening.", "week 9 of pregnancy","week9"));
        notifications.add(new Notification(10,"Your baby has finished the most critical part of development! Organs and structures are in place and ready to grow.", "week 10 of pregnancy","week10"));
        notifications.add(new Notification(11,"Your baby's hands will soon open and close into fists, and tiny tooth buds are appearing underneath the gums.", "week 11 of pregnancy","week11"));
        notifications.add(new Notification(12,"Your little one's teeny toes can curl, her brain is growing furiously, and her kidneys are starting to excrete urine.", "week 12 of pregnancy","week12"));
        notifications.add(new Notification(13,"It's the last week of the first trimester! Your baby now has exquisite fingerprints and is almost 3 inches long.", "week 13 of pregnancy","week13"));
        notifications.add(new Notification(14,"Your baby's tiny features are making different expressions. And you may be feeling more energetic and less nauseated.", "week 14 of pregnancy","week14"));
        notifications.add(new Notification(15,"Your baby can sense light and is forming taste buds. Have a stuffy nose? It's a surprising pregnancy side effect.", "week 15 of pregnancy","week15"));
        notifications.add(new Notification(16,"Get ready for a growth spurt. In the next few weeks, your baby will double his weight and add inches to his length.", "week 16 of pregnancy","week16"));
        notifications.add(new Notification(17,"Your baby's skeleton is changing from soft cartilage to bone, and the umbilical cord is growing stronger and thicker.", "week 17 of pregnancy","week17"));
        notifications.add(new Notification(18,"Your baby's genitals are developed enough to see on an ultrasound. Hungry? An increase in appetite is normal now.", "week 18 of pregnancy","week18"));
        notifications.add(new Notification(19,"Go ahead and sing: Your baby may be able to hear you! And if your sides are aching, it could be round ligament pain.", "week 19 of pregnancy","week19"));
        notifications.add(new Notification(20,"Congratulations, you're at the halfway mark in your pregnancy! Your baby is swallowing more now and producing meconium", "week 20 of pregnancy","week20"));
        notifications.add(new Notification(21,"Feeling your baby move? Those early flutters will turn into full fledged kicks. Cool fact: She has eyebrows now!", "week 21 of pregnancy","week21"));
        notifications.add(new Notification(22,"Your baby is starting to look like a miniature newborn. And your growing belly may be turning into a hand magnet.", "week 22 of pregnancy","week22"));
        notifications.add(new Notification(23,"When you're on the move, your baby can feel the motion. Pretty soon, you may notice swelling in your ankles and feet.", "week 23 of pregnancy","week23"));
        notifications.add(new Notification(24,"Your baby is long and lean, like an ear of corn. And your growing uterus is now the size of a soccer ball.", "week 24 of pregnancy","week24"));
        notifications.add(new Notification(25,"Your little one is starting to add some baby fat and grow more hair. Your hair may be looking extra lustrous, too.", "week 25 of pregnancy","week25"));
        notifications.add(new Notification(26,"Your baby is inhaling and exhaling small amounts of amniotic fluid, which is good practice for breathing.", "week 26 of pregnancy","week26"));
        notifications.add(new Notification(27,"Feel a tickle? It may be your baby hiccupping. He's also opening and closing his eyes and even sucking his fingers.", "week 27 of pregnancy","week27"));
        notifications.add(new Notification(28,"Welcome to your last trimester! Your developing baby's eyes may be able to see light filtering in through your womb.", "week 28 of pregnancy","week28"));
        notifications.add(new Notification(29,"Your baby's muscles and lungs are continuing to mature, and her head is growing to make room for her developing brain.", "week 29 of pregnancy","week29"));
        notifications.add(new Notification(30,"Your baby now weighs almost 3 pounds. Meanwhile, you may be battling mood swings, clumsiness, and fatigue.", "week 30 of pregnancy","week30"));
        notifications.add(new Notification(31,"Your baby's strong kicks might be keeping you up at night and you may be feeling Braxton Hicks contractions, too.", "week 31 of pregnancy","week31"));
        notifications.add(new Notification(32,"Your baby is plumping up! Meanwhile, your expanding uterus may cause heartburn and shortness of breath.", "week 32 of pregnancy","week32"));
        notifications.add(new Notification(33,"With your baby now weighing a little over 4 pounds, you might be waddling and having trouble getting comfy in bed.", "week 33 of pregnancy","week33"));
        notifications.add(new Notification(34,"Your baby's central nervous system and lungs are maturing, and dizziness and fatigue may be slowing you down.", "week 34 of pregnancy","week34"));
        notifications.add(new Notification(35,"Your baby is too snug in your womb to do somersaults, but you'll still feel frequent if less dramatic movements.", "week 35 of pregnancy","week35"));
        notifications.add(new Notification(36,"Your baby is gaining about an ounce a day. You may feel her \"drop\" down into your pelvis as you approach your due date.", "week 36 of pregnancy","week36"));
        notifications.add(new Notification(37,"Your baby's brain and lungs are continuing to mature. You may have more vaginal discharge and occasional contractions.", "week 37 of pregnancy","week37"));
        notifications.add(new Notification(38,"Your baby has a firm grasp, which you'll soon be able to test in person! Meanwhile, watch out for signs of preeclampsia.", "week 38 of pregnancy","week38"));
        notifications.add(new Notification(39,"Your baby is full term this week and waiting to greet the world! If your water breaks, call your healthcare provider.", "week 39 of pregnancy","week39"));
        notifications.add(new Notification(40,"Your baby is the size of a small pumpkin! Don't worry if you're still pregnant it's common to go past your due date.", "week 40 of pregnancy","week40"));
        notifications.add(new Notification(41,"As cozy as he is, your baby can't stay inside you much longer. You'll go into labor or be induced soon.", "week 41 of pregnancy","week41"));
    }
    public static Notification  getNotificationById(int id){
        Notification n = new Notification();
        for (int i = 1; i < notifications.size(); i++) {
            if(i==id-1) {
                n = notifications.get(i);
            }
        }
        return n;
    }

}
