package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.ExplosivePotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class CrateOfFireBombs extends BaseRelic {
    private static final String NAME = "CrateOfFireBombs";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;


    public CrateOfFireBombs() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    @Override
    public void onUsePotion() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        ExplosivePotion potion = new ExplosivePotion();
        potion.use(AbstractDungeon.getRandomMonster());
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (!CrateOfFireBombs.ID.equals(r.relicId)) {
                r.onUsePotion();
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new CrateOfFireBombs();
    }
}
