package reddit_grab_bag;

import basemod.*;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import reddit_grab_bag.cards.BaseCard;
import reddit_grab_bag.cards.PlasmaLance;
import reddit_grab_bag.relics.BaseRelic;
import reddit_grab_bag.util.GeneralUtils;
import reddit_grab_bag.util.KeywordInfo;
import reddit_grab_bag.util.TextureLoader;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglFileHandle;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;

import java.util.*;

@SpireInitializer
public class RedditGrabBagMod implements
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber,
        PostInitializeSubscriber,
        PostPotionUseSubscriber {
    public static ModInfo info;
    public static String modID; //Edit your pom.xml to change this
    static { loadModInfo(); }
    private static final String resourcesFolder = checkResourcesPath();
    public static final Logger logger = LogManager.getLogger(modID); //Used to output to the console.

    public static Properties defaultSettings = new Properties();
    public static final String ENABLE_CUSTOM_RELICS = "enableIroncladRelics";
    public static final String ENABLE_CUSTOM_CARDS = "enableCustomCards";
    public static boolean enableCustomRelics = true;
    public static boolean enableCustomCards = true;

    //This is used to prefix the IDs of various objects like cards and relics,
    //to avoid conflicts between different mods using the same name for things.
    public static String makeID(String id) {
        return modID + ":" + id;
    }

    //This will be called by ModTheSpire because of the @SpireInitializer annotation at the top of the class.
    public static void initialize() {
        new RedditGrabBagMod();
    }

    public RedditGrabBagMod() {
        BaseMod.subscribe(this); //This will make BaseMod trigger all the subscribers at their appropriate times.
        logger.info(modID + " subscribed to BaseMod.");

        defaultSettings.setProperty(ENABLE_CUSTOM_RELICS, "TRUE");
        defaultSettings.setProperty(ENABLE_CUSTOM_CARDS, "TRUE");

        try {
            SpireConfig config = new SpireConfig(info.Name, modID + "Config", defaultSettings);
            config.load();
            enableCustomRelics = config.getBool(ENABLE_CUSTOM_RELICS);
            enableCustomCards = config.getBool(ENABLE_CUSTOM_CARDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivePostInitialize() {
        //This loads the image used as an icon in the in-game mods menu.
        Texture badgeTexture = TextureLoader.getTexture(imagePath("badge.png"));
        //Set up the mod information displayed in the in-game mods menu.
        //The information used is taken from your pom.xml file.

        ModPanel settingsPanel = new ModPanel();
        ModLabeledToggleButton enableCustomRelicsButton = new ModLabeledToggleButton("Enable Custom Relics",
                350.0f, 700.0f, Settings.GOLD_COLOR, FontHelper.charDescFont,
                enableCustomRelics, settingsPanel, (label) -> {},
                (button) -> {
                    enableCustomRelics = button.enabled;
                    try {
                        SpireConfig config = new SpireConfig(info.Name, modID + "Config", defaultSettings);
                        config.setBool(ENABLE_CUSTOM_RELICS, enableCustomRelics);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        ModLabeledToggleButton enableCustomCardsButton = new ModLabeledToggleButton("Enable Custom Cards",
                350.0f, 660.0f, Settings.GOLD_COLOR, FontHelper.charDescFont,
                enableCustomCards, settingsPanel, (label) -> {},
                (button) -> {
                    enableCustomCards = button.enabled;
                    try {
                        SpireConfig config = new SpireConfig(info.Name, modID + "Config", defaultSettings);
                        config.setBool(ENABLE_CUSTOM_CARDS, enableCustomCards);
                        config.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        settingsPanel.addUIElement(new ModLabel("Must restart game to take effect", 350.0F, 750.0F, settingsPanel, me -> {}));
        settingsPanel.addUIElement(enableCustomRelicsButton);
        settingsPanel.addUIElement(enableCustomCardsButton);

        //If you want to set up a config panel, that will be done here.
        //You can find information about this on the BaseMod wiki page "Mod Config and Panel".
        BaseMod.registerModBadge(badgeTexture, info.Name, GeneralUtils.arrToString(info.Authors), info.Description, settingsPanel);
    }

    /*----------Localization----------*/

    //This is used to load the appropriate localization files based on language.
    private static String getLangString()
    {
        return Settings.language.name().toLowerCase();
    }
    private static final String defaultLanguage = "eng";

    public static final Map<String, KeywordInfo> keywords = new HashMap<>();

    @Override
    public void receiveEditCards() {
        if (enableCustomCards) {
            new AutoAdd(modID) //Loads files from this mod
                    .packageFilter(BaseCard.class) //In the same package as this class
                    .setDefaultSeen(true) //And marks them as seen in the compendium
                    .cards(); //Adds the cards
        }
    }

    @Override
    public void receiveEditStrings() {
        /*
            First, load the default localization.
            Then, if the current language is different, attempt to load localization for that language.
            This results in the default localization being used for anything that might be missing.
            The same process is used to load keywords slightly below.
        */
        loadLocalization(defaultLanguage); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage.equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void receiveEditRelics() {
        if (enableCustomRelics) {
            new AutoAdd(modID).packageFilter(BaseRelic.class)
                    .any(BaseRelic.class, (info, relic) -> {
                        BaseMod.addRelic(relic, relic.relicType);
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    });
        }
    }

    private void loadLocalization(String lang) {
        //While this does load every type of localization, most of these files are just outlines so that you can see how they're formatted.
        //Feel free to comment out/delete any that you don't end up using.
        BaseMod.loadCustomStringsFile(CardStrings.class,
                localizationPath(lang, "CardStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                localizationPath(lang, "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                localizationPath(lang, "RelicStrings.json"));
    }

    @Override
    public void receivePostPotionUse(AbstractPotion potion) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof BaseRelic) {
                BaseRelic br = (BaseRelic) r;
                br.onAfterPotionUse();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(PlasmaLance.ID)) {
                PlasmaLance pl = (PlasmaLance) c;
                pl.checkAndSetCost();
            }
        }
    }
    //These methods are used to generate the correct filepaths to various parts of the resources folder.
    public static String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public static String audioPath(String file) {
        return resourcesFolder + "/audio/" + file;
    }
    public static String imagePath(String file) {
        return resourcesFolder + "/images/" + file;
    }
    public static String characterPath(String file) {
        return resourcesFolder + "/images/character/" + file;
    }
    public static String powerPath(String file) {
        return resourcesFolder + "/images/powers/" + file;
    }
    public static String relicPath(String file) {
        return resourcesFolder + "/images/relics/" + file;
    }

    /**
     * Checks the expected resources path based on the package name.
     */
    private static String checkResourcesPath() {
        String name = RedditGrabBagMod.class.getName(); //getPackage can be iffy with patching, so class name is used instead.
        int separator = name.indexOf('.');
        if (separator > 0)
            name = name.substring(0, separator);

        FileHandle resources = new LwjglFileHandle(name, Files.FileType.Internal);

        if (!resources.exists()) {
            throw new RuntimeException("\n\tFailed to find resources folder; expected it to be at  \"resources/" + name + "\"." +
                    " Either make sure the folder under resources has the same name as your mod's package, or change the line\n" +
                    "\t\"private static final String resourcesFolder = checkResourcesPath();\"\n" +
                    "\tat the top of the " + RedditGrabBagMod.class.getSimpleName() + " java file.");
        }
        if (!resources.child("images").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'images' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "images folder is in the correct location.");
        }
        if (!resources.child("localization").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'localization' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "localization folder is in the correct location.");
        }

        return name;
    }

    /**
     * This determines the mod's ID based on information stored by ModTheSpire.
     */
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo)->{
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(RedditGrabBagMod.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        }
        else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }
}
