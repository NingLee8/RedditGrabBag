package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class SteppedOnRoach extends BaseRelic{

    private static final String NAME = "SteppedOnRoach";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.FLAT;

    public SteppedOnRoach() {
        super(ID, NAME, AbstractCard.CardColor.RED, RARITY, SOUND);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            if (m.currentHealth < AbstractDungeon.player.currentHealth) {
                this.addToBot(new RelicAboveCreatureAction(m, this));
                this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, 1, false), 1, true));
            }
        }
    }

    public AbstractRelic makeCopy() {
        return new SteppedOnRoach();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
