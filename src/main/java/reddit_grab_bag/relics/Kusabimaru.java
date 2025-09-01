package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class Kusabimaru extends BaseRelic {

    private static final String NAME = "Kusabimaru";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private AbstractPlayer p;

    public Kusabimaru() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.owner != AbstractDungeon.player && info.type.equals(DamageInfo.DamageType.NORMAL) &&
                info.output > 0 &&
                AbstractDungeon.player.currentBlock == 0 &&
                damageAmount == 0) {
            this.addToTop(new RelicAboveCreatureAction(info.owner, this));
            this.addToTop(new DamageAction(info.owner, new DamageInfo(null, 12, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        return damageAmount;
    }

    public AbstractRelic makeCopy() {
        return new Kusabimaru();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
