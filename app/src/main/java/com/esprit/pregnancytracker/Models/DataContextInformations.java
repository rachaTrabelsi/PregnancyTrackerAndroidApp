package com.esprit.pregnancytracker.Models;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 04/01/2018.
 */

public class DataContextInformations {
    public static List<alimentationtoeat> alimtoeatlist = new ArrayList<>();

    public static void setAlimList() {
        alimtoeatlist.add(new alimentationtoeat(1,"Fortified Breakfast Cereal", "You knew folate was important before conception and during your first few weeks of pregnancy, but your needs for the B vitamin stay high the whole nine months. Experts advise getting 400 micrograms per day through vitamin supplements or fortified foods (breakfast cereal is an easy way to do it, since many brands contain 400 micrograms per bowl), and another 200 micrograms through foods that are naturally high in folate, such as asparagus and black-eyed peas.","fortified"));
        alimtoeatlist.add(new alimentationtoeat(2,"Dried Beans & Lentils", "All women need 10 extra grams of protein a day during pregnancy (for a total of at least 60 grams); beans and lentils are an excellent source, with about 15 grams per cup. They're also high in fiber, which helps to combat constipation. And 1 cup of cooked lentils meets half of your daily folate requirement. \"Add them to rice dishes and salads,\" suggests Lola O'Rourke, RD, spokesperson for the American Dietetic Association.","beans"));
        alimtoeatlist.add(new alimentationtoeat(3,"Broccoli", "It's not only packed with nutrients that are necessary for a healthy pregnancy -- such as calcium and folate -- but broccoli is also rich in fiber and disease-fighting antioxidants. And since it contains plenty of vitamin C, this popular green vegetable will help your body absorb iron when it's eaten with an iron-rich food, such as whole wheat pasta or brown rice.","broccoli"));
        alimtoeatlist.add(new alimentationtoeat(4,"Nonfat Milk", "Your body absorbs roughly twice as much calcium from foods while you're pregnant, so your daily needs remain the same. But since most of us get too little calcium to begin with, drinking more nonfat milk is a smart move. Each 8-ounce glass supplies about 30 percent of the recommended dietary allowance of 1,000 milligrams.","nonfat"));
        alimtoeatlist.add(new alimentationtoeat(5,"Bananas", "Bananas are rich in potassium and offer quick energy to fight off pregnancy fatigue. \"They're also easy on your stomach if you're nauseated,\" says O'Rourke. Slice them up into cereal or whip one into a breakfast smoothie with yogurt, berries, ice, and a splash of orange juice.","bananas"));
        alimtoeatlist.add(new alimentationtoeat(6,"Lean Meat", "Your daily iron needs double during pregnancy, so it's important to include plenty of iron-rich foods now. \"If you don't have good iron stores, you're more likely to feel tired,\" warns Jo Ann Hattner, RD, a dietitian in Palo Alto, California. Meat delivers a form of iron that's easily absorbed by your body.","leanmeat"));
        alimtoeatlist.add(new alimentationtoeat(7,"Cheese", "Soft cheeses are off-limits, but varieties such as cheddar and mozzarella can be a big help in meeting your calcium requirements -- each ounce contains between 150 and 200 milligrams. Cheese is also high in protein.","cheese"));
        alimtoeatlist.add(new alimentationtoeat(8,"Eggs", "Many women develop aversions to meat while pregnant. Eggs are an excellent alternative protein source, since they contain all the essential amino acids your body needs, says Hattner. There's nothing better for a quick dinner than an omelet with lots of chopped vegetables and a bit of cheese. If cooking aromas make you feel sick, hard-boil a batch of eggs to keep on hand in the refrigerator: Eat them whole for grab-and-go breakfasts and snacks, or chop them up into green salads.","eggs"));
        alimtoeatlist.add(new alimentationtoeat(9,"Oatmeal", "It's easy to get your day off to an energizing start by trading in your usual morning bagel or muffin for a bowl of oatmeal a few times a week. Why? Complex carbohydrates like oatmeal keep you satisfied longer, and the oat bran it contains can help lower your cholesterol levels. Instead of buying high-sugar flavored oatmeal, cook up the plain kind and swirl in a teaspoon or two of maple syrup or jelly.","oatmeal"));
        alimtoeatlist.add(new alimentationtoeat(10,"Leafy Greens", "Cooked spinach has high levels of folate and iron, and kale and turnip greens are both good calcium sources. Increase the nutrient value of your salads by passing up traditional iceberg in favor of darker-colored lettuces (the deep colors signal higher vitamin content). You can also add greens to a sandwich or stir them into soups and pasta dishes.","leafygreens"));
        alimtoeatlist.add(new alimentationtoeat(11,"Whole-Grain Bread", "By swapping your traditional white bread for a whole-grain variety, you can make sure you're consuming the recommended 20 to 35 daily grams of fiber (scan labels to find a loaf that offers at least 2 grams of fiber per slice). Whole-grain bread also supplies you with a good share of your iron and zinc.","bred"));
        alimtoeatlist.add(new alimentationtoeat(12,"Oranges", "They're packed with vitamin C, folate, and fiber, and since they're nearly 90 percent water, they'll also help you meet your daily fluid needs (skimping on your fluid intake can leave you feeling fatigued).","oranges"));
        alimtoeatlist.add(new alimentationtoeat(13,"Nuts & Nut Butters", "Fat is critical for your baby's brain development and it also helps keep you fuller longer. Experts recommend replacing some saturated fats (such as those found in meat and butter) with unsaturated, a form of heart-healthy fat found in nuts. But because they are high in fat and calories, stick to 1-ounce servings of nuts and 2-tablespoon servings of nut butters. There is one caveat, however. If you have any sort of allergy, experts recommend that you avoid highly allergenic foods, such as peanuts, during your pregnancy; some data suggests that babies can be sensitized to certain foods in utero, raising their risk of food allergies later on in childhood.","nuts"));
        alimtoeatlist.add(new alimentationtoeat(14,"Soy Foods", "It's perfectly safe to follow your vegetarian eati...","soy"));
        alimtoeatlist.add(new alimentationtoeat(15,"Dried Fruit", "It's a tasty, portable snack that's especially helpful when you're craving something sweet. Choose dried fruits such as apricots, cherries, and cranberries (which can also help to prevent urinary tract infections), but stay away from dried bananas, since they're processed in oil and loaded with fat.","dried"));



    }

    public static   List<alimentationnottoeat> alimnottoeat = new ArrayList<>();

    public static void setAlimnottoeat() {
        alimnottoeat.add(new alimentationnottoeat(1,"Watch out for: Cold cuts and deli meat", "Why: As with soft cheeses, there's a small risk that harmful listeria bacteria may lurk in fresh-from-the-deli-counter meats like turkey and ham. Dr. Morse also advises steering clear of whole, cooked rotisserie chickens and turkey breasts if they're being stored in a refrigerated case. Listeria can survive cold temps, which means there's still an off-chance they could make you sick; however, chicken that has been recently cooked and is still under the warmer is fine.","coldmeats"));
        alimnottoeat.add(new alimentationnottoeat(2,"Watch out for: High-mercury fish", "Why: Certain fish -- mostly big, top-of-the-food chain types -- contain high levels of mercury, which isn't good for anyone's health (pregnant or not), but they can be particularly harmful to a developing baby's nervous system, lungs, kidneys, vision, and hearing. On the Do-Not-Eat list: shark, swordfish, tilefish, king mackerel, orange roughy, grouper, tuna steaks, saltwater bass, and canned solid white albacore tuna (which is bigger, and has therefore more mercury than the smaller tunas used in the kind labeled \"chunk light\"), according to Redfern.","mercuryfish"));
        alimnottoeat.add(new alimentationnottoeat(3,"Watch out for: Sushi and sashimi", "Why: There's a slight chance that raw fish may contain bacteria or microbes that could cause food poisoning. \"But the main concern with sushi is that in the unlikely event that you get a parasite, it's not only exceedingly unpleasant, it's harder to treat in pregnancy. The parasite can also take vital nutrients away from your growing baby,\" says Dr. Morse. Plus, some of the most popular sushi rolls (like spicy tuna) may contain too-high mercury levels.","sushi"));
        alimnottoeat.add(new alimentationnottoeat(4,"Watch out for: Raw or runny eggs", "Why: There's a slight risk of salmonella and other food-borne illnesses from eggs cooked sunny side up, and from sources of uncooked eggs such as Caesar salad dressing or raw cookie dough. \"Your immune system is weaker when you're pregnant, which means that a bug that wouldn't have caused food poisoning before may affect you more now,\" says Redfern. Also, vomiting or diarrhea that would have just been uncomfortable and annoying before you were pregnant can more easily trigger dehydration now, which has the potential to affect fetal growth and in rare cases can lead to preterm labor.","sushi"));
        alimnottoeat.add(new alimentationnottoeat(5,"Watch out for: High amounts of coffee, soda, or any caffeinated beverage", "Why: Some research shows that lots of caffeine (considered to be more than two to three cups of coffee a day) can raise your risk of miscarriage. It has also been linked to preterm delivery and low birth weight. A Kaiser Permanente study, for example, found that pregnant women who consumed more than 200 milligrams of caffeine a day had double the miscarriage risk of those who had none.","coffe"));
        alimnottoeat.add(new alimentationnottoeat(6,"Watch out for: Saccharine", "Why: Experts advise avoiding saccharine, the stuff in Sweet N' Low, during pregnancy. \"Unlike other artificial sweeteners, like Equal or NutraSweet, saccharine can cross the placenta,\" Redfern says. \"Even though it's been shown to be harmless in people, we recommend skipping it just to be extra cautious.","saccharine"));
        alimnottoeat.add(new alimentationnottoeat(7,"Watch out for: Herbal teas", "Why: Some herbs can have medicinal effects just like actual drugs, which is why the FDA and many doctors advise steering clear of certain varieties. Even though the amount of herbs used in commercial teas isn't believed to be strong enough to cause problems, because the FDA doesn't regulate them, there's no way of knowing exactly how potent they are. \"I generally recommend patients avoid teas containing chamomile and hibiscus because some evidence suggests that in high amounts they may cause problems like preterm labor,\" says Dr. Morse. \"But the main reason we're so cautious is that we just don't have a lot of data here.\" Comfrey and sassafras are other herbs that experts recommend pregnant women avoid.","herbalteas"));
        alimnottoeat.add(new alimentationnottoeat(8,"Watch out for: Spicy foods", "Why: Piling on those jalapenos can give pregnant women major heartburn, something you're already prone to these days. While this won't harm your baby, it can feel lousy for you. And women with GERD (gastroesophageal reflux disease, a more severe, chronic form of heartburn) should take extra care to avoid spicy dishes.","spicyfood"));
        alimnottoeat.add(new alimentationnottoeat(9,"Watch out for: Alcohol", "Why: It's a well-established fact that drinking alcohol frequently during pregnancy can seriously harm an unborn baby, causing a number of physical and mental birth defects. But we don't yet know exactly how much is harmful. There's no research, for instance, on the effects of having just a couple of drinks during pregnancy, so experts can't say what -- if anything -- is considered a safe amount. They do know that alcohol crosses the placenta right away, so your baby drinks whatever you do. \"Since we don't know how much alcohol it actually takes to harm a fetus, it's best to just have none,\" says Dr. Morse.","alcoholinformation"));

          }


    public static   List<sleep> sleepList = new ArrayList<>();

    public static void setSleep() {
        sleepList.add(new sleep(1,"Drink up", "Drink plenty of fluids during the day, but cut down before bedtime to minimize frequent nighttime urination.","drinkup"));
        sleepList.add(new sleep(2,"Keep moving", "Exercise regularly for optimum health, and to improve circulation (thus reducing nighttime leg cramps). Avoid exercising late in the day -- exercise releases adrenaline that can keep you awake at night.","keepmoving"));
        sleepList.add(new sleep(3,"Reduce stress and anxiety", "Stress and anxiety are key culprits in preventing a good night's sleep. Remember that worrying won't help you, but talking about your problems will. Find a friend or a professional who can listen and help you if there are issues in your life that are causing you to worry or feel upset.","stress"));
        sleepList.add(new sleep(4,"Get into a routine", "If you establish a consistent, soothing, and comforting evening routine, you'll be able to relax and drift off to sleep with more ease. As bedtime approaches, try a few soothing rituals like drinking a cup of caffeine-free tea or hot milk, reading a chapter of a pleasant book, taking a warm shower, getting a shoulder massage, or having your hair gently brushed.","routine"));
        sleepList.add(new sleep(5,"Get into position", "After 20 weeks, sleep on your left side to allow for the best blood flow to the fetus and to your uterus and kidneys. Avoid lying flat on your back.","sleepleftside"));
        sleepList.add(new sleep(6,"Keep heartburn at bay", "To prevent heartburn, don't recline for an hour or two after a meal. If heartburn is a problem, sleep with your head elevated on pillows. Also, avoid spicy, fried, or acidic foods (such as tomato products), as they may worsen symptoms.","heartburn"));
        sleepList.add(new sleep(7,"Nap during the day", " If you're not getting enough rest at night, take a nap to help reduce fatigue. Find a quiet spot and relax, even if only for a half-hour nap.","nap"));
        sleepList.add(new sleep(8,"Support your body", "Use a special pregnancy body pillow or a regular pillow to support your body. For comfort, try sleeping on your side with one pillow under your knee and another under your belly.","supportbody"));
        sleepList.add(new sleep(9,"Watch your diet", " Completely eliminate caffeine and alcohol to prevent insomnia. If nausea is a problem for you, try eating frequent bland snacks (like crackers) throughout the day. Keeping your stomach slightly full helps keep nausea at bay. Eat a well-balanced diet. Not only is this crucial for your health and that of your baby, but getting the necessary nutrients will help keep you feeling satisfied -- which will help you sleep more soundly.","diet"));
        sleepList.add(new sleep(10,"Get help", "See your doctor for advice if insomnia persists. Now more than ever, it's important to get the rest you need!","help"));
        }
    public static   List<sport> sportList = new ArrayList<>();

    public static void setSport() {
        sportList.add(new sport(1,"Swimming", "As a mom-to-be, you're focused on doing everything you can to have a healthy baby. Exercise is good for both you and your little one. In fact, the right moves can ease common discomforts like back pain and sleep troubles. One of the best exercises is swimming. It's gentle on the joints and relieves swollen ankles. And you'll feel light as a beach ball no matter how big your baby bump. Check with your health care provider before starting or continuing exercise in pregnancy.","swimming"));
        sportList.add(new sport(2,"Yoga", "Yoga strengthens core muscles, eases back pain, and helps you relax. And research shows that it may make labor shorter and more comfortable. Try a prenatal class, which is gentler and focuses on relaxation -- good prep for labor. Avoid \"hot yoga,\" and after your first trimester, don't lie on your back. If something doesn't feel right, check with a fitness expert.","yoga"));
        sportList.add(new sport(3,"Indoor Cycling", "Take a load off your legs! Cycling on a stationary bike is generally safe even if you're just starting an exercise program. Cycling is a good way to boost your heart rate without stressing your joints. As your belly grows, you can raise the handlebars for greater comfort.","indoor"));
        sportList.add(new sport(4,"Weight Training", "Light strength training can help you stay toned before and after delivery. If you were lifting weights before you got pregnant, chances are you can keep going as long as you go easy. Avoid heavy weights or routines where you have to lie flat on your back. If you weren't strength training before you got pregnant, find another exercise for now.","weighttraining"));
        sportList.add(new sport(5,"Brisk Walking", "Whether you're on a trail or a treadmill, walking can safely help tone muscles and improve your mood. It's also something most women can do right up to delivery. If you're just starting, try walking a semi-swift mile three days a week. Increase your time and speed a little each week, and build in hills as you get stronger.","walking"));
        sportList.add(new sport(6,"Low-Impact Aerobics", "Aerobics keep your heart and lungs strong, tone your body all over, and give you a burst of endorphins, a feel-good brain chemical. If you're a beginner, look for a low-impact aerobics class taught by a certified aerobics teacher.","aerobic"));
        sportList.add(new sport(7,"High-Intensity Sports", "If you regularly run or play tennis, you don't need to stop. As you get closer to your due date, run on flat, groomed surfaces to reduce impact and avoid spills. This may also be a good time to postpone racquet sports that require good balance and sudden changes in body position.","highsport"));
        sportList.add(new sport(8,"Abdominals", "Exercises for your abs can ease an aching back and help fight a \"sway back\" posture that may develop as the uterus gets heavier. Two safe options are:\n" +
                "\n" +
                "Kneeling Pelvic Tilt. On all fours with a flat back, tighten the abs and gently arch your back to the ceiling. Don't let your belly sag down.\n" +
                "Standing Pelvic Tilt. Back up to a wall, with your feet three inches out. Tighten your stomach and buttocks and press your low back to touch the wall.","abdominals"));
        sportList.add(new sport(9,"Stretching", "\"Tailor\" stretches target pelvic, hip, and thigh muscles. They can also help lessen low back pain. Try these:\n" +
                "\n" +
                "Tailor sit. Sit with your knees bent and ankles crossed. Lean forward a little, keeping your back straight.\n" +
                "Tailor press. Sit with your knees bent and the bottoms of your feet together. Place your hands under your knees. Press the knees down against your hands and your hands up against the knees. Hold a few seconds and release.","stretching"));
        sportList.add(new sport(10,"Kegels", "The beauty of Kegel exercises is that you can do them anytime, anywhere, without anyone knowing. Kegels strengthen the muscles that help hold up the uterus, bladder, and bowels, which helps labor and delivery. To do them, squeeze the pelvic muscles as if you're trying to stop urinating or passing gas. Hold for five seconds and relax. Repeat 10 times, five times a day.","kegels"));


         }
    public static   List<water> waterList = new ArrayList<>();

    public static void setWater() {
        waterList.add(new water(1,"Water Delivers Essentials To Your Fetus", "Ever wonder how all the good stuff in the prenatal vitamins and healthy foods you're faithfully consuming every day are shipped to your fetus? It all starts with water, \n" +
                "which helps your body absorb essential nutrients into the cells and transports vitamins, minerals and hormones to the blood cells. It's those nutrient rich blood cells \n" +
                "that reach the placenta and ultimately your baby \n" +
                "all with the help of H2O.","feutus"));
        waterList.add(new water(2,"Your Recommended Daily Water Intake Is Higher During Pregnancy", "To that end you will need more water to keep your system running for two during pregnancy. The Institute of Medicine says pregnant women in temperate climates should aim to drink 12 or \n" +
                "13 glasses they count a glass as eight ounces each day which is slightly more than the amount for non pregnant women around 11 glasses each day Try to space out your sips to keep them coming steadily throughout the day rather than gulping a lot at once which could leave you feeling uncomfortably full. Since most of us don't drink enough fluids, filling a water bottle or two every morning and keeping it handy all day takes the hassle out of hydration. Be sure to \n" +
                "sip before, during and after you work out, or if you find yourself outside on a hot day. Note, too, that if you feel thirsty, it's a sign that your body is already on its way to being \n" +
                "dehydrated.","waterinformationlist"));
        waterList.add(new water(3,"Water Can Help Prevent UTIs, Constipation And Hemorrhoids", "Now that you are pregnant, you are not just eating and drinking for two you are also excreting for two. That means you will have more trash to take out \n" +
                "of your system than ever before. Enter water, which dissolves the waste products and helps flush them from the kidneys. Drinking enough water also keeps your urine diluted, \n" +
                "which not only keeps things flowing but also keeps UTIs at bay urine that hangs out too long in your bladder can become a breeding ground for infection triggering bacteria, \n" +
                "as well as bladder infections and kidney infections which are are types of UTIs.","constipation"));
        waterList.add(new water(4,"Water Can Help With Fatigue, Headaches, Swelling And Overheating", "Is it crazy hot in here or are you pregnant? It's true, the heat is on high when you are expecting. But if you drink water during pregnancy, you can keep the body's \n" +
                "cooling system running smoothly  even when your inner thermostat is cranked all the way up by dispersing excess heat in the form of sweat.\n" +
                "An ample flow of fluids also keeps pregnancy fatigue in check one of the first symptoms of dehydration is exhaustion and can keep headaches at bay.\n" +
                "It also helps your body get rid of excess sodium among other things, minimizing swelling particularly swollen feet or ankles.","fatigue"));
        waterList.add(new water(5,"Ensuring Your Water's Safe", "It's never a bad idea to assess the cleanliness of your drinking water and that's especially true when you're pregnant. While most of the water you drink likely comes from public \n" +
                "water systems and is generally safe to drink, it can be contaminated with high levels of chemicals that could harm a fetus, including lead, mercury and arsenic. In addition, there is \n" +
                "increasing evidence that BPA (an industrial chemical that mimics estrogen and is found in some plastics) can be problematic when you are expecting. Learn more on how to ensure your water \n" +
                "supply is safe to drink when you are pregnant.","safeDrinkingWater"));
        waterList.add(new water(6,"Water Isn't The Only Way To Hydrate", "Water is the best drink around, but if you are all watered out? There are plenty of other liquids that make the cut: Milk (an 8 ounce glass of skim yields just over seven ounces of water),\n" +
                " sparkling or flavored waters, fruit and vegetable juices (watch out for added sugar in fruit juice and added sodium in veggie drinks) and decaffeinated teas are all great choices \n" +
                " just be sure you keep an eye on pasteurization, calorie info and all that. You should, however, limit your intake of soda (nothing but empty calories), as well as other beverages \n" +
                " containing caffeine, since they have a diuretic effect (besides the other reasons to cut the caffeine during pregnancy).\n" +
                "\n" +
                "Keep in mind, too, that about 20 percent of our daily water intake comes from food sources. Fruits pack the most water: One cup of watermelon or cantaloupe provides just under \n" +
                "five ounces of water a medium-size pear or one cup of strawberries provides about four and a half ounces a medium size orange has four ounces. Got the sniffles? A cup of chicken \n" +
                "soup yields roughly six ounces of water.","hydratation"));
        }
    public static List<alimentationtoeat>  getalimentationtoealt(){

        for (int i = 1; i < alimtoeatlist.size(); i++) {
            alimtoeatlist.get(i);
        }
        return alimtoeatlist;
    }
    public static List<alimentationnottoeat>  getalimentationnottoealt(){

        for (int i = 1; i < alimnottoeat.size(); i++) {
            alimnottoeat.get(i);
        }
        return alimnottoeat;
    }
    public static List<water>  getwater(){

        for (int i = 1; i < waterList.size(); i++) {
            waterList.get(i);
        }
        return waterList;
    }
    public static List<sleep>  getsleep(){

        for (int i = 1; i < sleepList.size(); i++) {
            sleepList.get(i);
        }
        return sleepList;
    }
    public static List<sport>  getsport(){
        return sportList;
    }
}
