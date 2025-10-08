package reddit_grab_bag.relics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class FalseIdol extends BaseRelic{
    private static final String NAME = "FalseIdol";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    private boolean firstTurn = true;
    private boolean showCounter = false;

    public FalseIdol() {
        super(ID, NAME, AbstractCard.CardColor.RED, RARITY, SOUND);
    }

    @Override
    public void atPreBattle() {
        this.counter = -1;
        firstTurn = true;
        showCounter = true;
    }

    @Override
    public void atTurnStart() {
        if (!firstTurn) {
            this.counter++;
        }
        else {
            firstTurn = false;
        }
        if (this.counter < 0) {
            this.addToBot(new LoseEnergyAction(1));
        }
        else if (this.counter > 0) {
            this.addToBot(new GainEnergyAction(this.counter));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
        if (showCounter) {
            FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, Integer.toString(this.counter),this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
        }
    }


    public void onVictory() {
        showCounter = false;
    }


    public AbstractRelic makeCopy() {
        return new FalseIdol();
    }
}
