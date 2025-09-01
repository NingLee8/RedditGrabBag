package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class LeadBalloon extends BaseRelic{

    private static final String NAME = "LeadBalloon";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static boolean usedThisTurn = false;


    public LeadBalloon() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
        usedThisTurn = false;
        this.grayscale = false;
    }
    
    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (!usedThisTurn) {
            if (drawnCard.costForTurn == 0) {
                this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
                usedThisTurn = true;
                this.grayscale = true;
            }
        }
    }
    
    @Override
    public void atTurnStart() {
        usedThisTurn = false;
        this.grayscale = false;
    }

    @Override
    public void onVictory() {
        usedThisTurn = false;
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new LeadBalloon();
    }
}
