package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class Ukulele extends BaseRelic{

    private static final String NAME = "Ukulele";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private AbstractPlayer p;

    public Ukulele() {
        super(ID, NAME, AbstractCard.CardColor.RED, RARITY, SOUND);
        this.p = AbstractDungeon.player;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 1), 1));
        this.addToBot(new RelicAboveCreatureAction(p, this));

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Ukulele();
    }

}
