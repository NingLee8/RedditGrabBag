package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.BlockPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class BottomlessBottle extends BaseRelic{
    private static final String NAME = "BottomlessBottle";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static boolean firstTurn = true;

    public BottomlessBottle() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
        firstTurn = true;
    }

    @Override
    public void onEndOfStartTurn(){
        if (!firstTurn) {
            return;
        }
        ArrayList<String> potions = PotionHelper.getPotions(AbstractDungeon.player.chosenClass, false);
        potions.remove("FairyPotion");
        potions.remove("SmokeBomb");
        potions.remove("SneckoOil");
        potions.remove("DistilledChaos");
        String randomKey = potions.get(AbstractDungeon.potionRng.random(potions.size() - 1));
        AbstractPotion potion = PotionHelper.getPotion(randomKey);
        if (!potion.isThrown) {
            potion.use(AbstractDungeon.player);
        } else {
            potion.use(AbstractDungeon.getRandomMonster());
        }
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        firstTurn = false;
    }

    public void atPreBattle() {
        firstTurn = true;
    }

    public void onVictory() {
        firstTurn = true;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new BottomlessBottle();
    }
}
