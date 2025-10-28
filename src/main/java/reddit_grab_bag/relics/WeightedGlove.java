package reddit_grab_bag.relics;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reddit_grab_bag.actions.WeightedGloveAction;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class WeightedGlove extends BaseRelic implements CustomSavable<Integer> {

    private static final String NAME = "WeightedGlove";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY;
    public boolean canDraw = true;
    private int originalHandSize = 5;

    public WeightedGlove() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    @Override
    public void onEndOfStartTurn(){
        this.addToBot(new WeightedGloveAction(this));
    }

    @Override
    public void atTurnStart() {
        canDraw = true;
    }

    @Override
    public void atPreBattle() {
        canDraw = true;
    }

    @Override
    public void onEquip() {
        this.originalHandSize = AbstractDungeon.player.masterHandSize;
        AbstractDungeon.player.masterHandSize = 10;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.masterHandSize = this.originalHandSize;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new WeightedGlove();
    }

    @Override
    public Integer onSave() {
        return this.originalHandSize;
    }

    @Override
    public void onLoad(Integer savedValue) {
        if (savedValue != null) {
            this.originalHandSize = savedValue;
        }
    }
}
