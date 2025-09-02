package reddit_grab_bag.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ForgeAction extends AbstractGameAction {

    private final AbstractPlayer p;

    private final boolean upgraded;

    public ForgeAction(boolean upgraded) {
        this.upgraded = upgraded;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
            }
            else if (this.p.hand.size() == 1) {
                addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, getMagicNumber(this.p.hand.getBottomCard())), getMagicNumber(this.p.hand.getBottomCard())));

                this.p.hand.moveToExhaustPile(this.p.hand.getBottomCard());
                tickDuration();
            }
            else {
                AbstractDungeon.handCardSelectScreen.open("Exhaust", 1, false);
                tickDuration();
            }
        }
        else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    int magicNumber = getMagicNumber(c);

                    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, magicNumber), magicNumber));

                    this.p.hand.moveToExhaustPile(c);
                }
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            }
            tickDuration();
        }
    }

    private int getMagicNumber(AbstractCard card) {
        int energy = (card.costForTurn == -1) ? EnergyPanel.getCurrentEnergy() : card.costForTurn;
        return upgraded ? energy + 1 : energy;
    }
}

