package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class BloodMoney extends BaseRelic {


    private static final String NAME = "BloodMoney";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public BloodMoney() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    @Override
    public void onLoseHp(int damageAmount) {
        if (damageAmount > 0) {
            this.flash();
            AbstractDungeon.effectList.add(new RainingGoldEffect(damageAmount * 2, true));
            this.addToBot(new GainGoldAction(damageAmount));
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
