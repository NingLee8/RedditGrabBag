package reddit_grab_bag.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;
import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class PrismaticFormPower extends  BasePower{

    public static final String POWER_ID = makeID("PrismaticFormPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final ArrayList<AbstractCard> allPowers = new ArrayList<>();

    public PrismaticFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (AbstractCard.CardType.POWER.equals(card.type) &&
                    !card.hasTag(AbstractCard.CardTags.HEALING) &&
                    !card.rarity.equals(AbstractCard.CardRarity.SPECIAL)) {
                allPowers.add(card);
            }
        }
    }

    @Override
    public void atStartOfTurn (){
        for(int i = 0; i < this.amount; ++i) {
            AbstractCard card = allPowers.get(cardRandomRng.random(allPowers.size() - 1));
            card.setCostForTurn(0);
            this.addToBot(new MakeTempCardInHandAction(card));
        }
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }

    }
}
