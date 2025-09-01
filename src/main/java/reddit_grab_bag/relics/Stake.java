package reddit_grab_bag.relics;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class Stake extends BaseRelic{

    private static final String NAME = "Stake";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.HEAVY;
    private final AbstractPlayer p;
    public boolean usedThisTurn;

    public Stake() {
        super(ID, NAME, AbstractCard.CardColor.RED, RARITY, SOUND);
        p = AbstractDungeon.player;
        this.usedThisTurn = false;
    }

    @Override
    public void onEndOfStartTurn() {
        // Glow all cards red
        for (AbstractCard c : p.hand.group) {
            c.glowColor = new Color(1.0F, 0.3F, 0.3F, 1.0F);
            c.isCostModifiedForTurn = true;
        }
    }

    @Override
    public void atTurnStart() {
        this.usedThisTurn = false;
    }

    @Override
    public void onVictory() {
        this.usedThisTurn = false;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (!this.usedThisTurn && !c.isInAutoplay) {
            for (AbstractCard card : p.hand.group) {
                card.glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);
                card.triggerOnGlowCheck();
                card.isCostModifiedForTurn = false;
            }
            this.addToBot(new LoseHPAction(p, p, c.costForTurn));
            c.setCostForTurn(0);
            this.usedThisTurn = true;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Stake();
    }

}
