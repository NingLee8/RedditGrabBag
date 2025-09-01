package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class PhantomKunai extends BaseRelic{

    private static final String NAME = "PhantomKunai";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;
    private static AbstractPlayer p;

    public PhantomKunai() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
        p = AbstractDungeon.player;
        this.counter = 0;
    }

    public void atPreBattle() {
        this.counter = 0;
    }

    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type.equals(AbstractCard.CardType.ATTACK)) {
            counter++;
        }
    }


    public void atTurnStart() {
        if (this.counter > 1) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.counter), this.counter));
            this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.counter), this.counter));
        }
        this.counter = 0;
    }

    public void onVictory() {
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new PhantomKunai();
    }

}
