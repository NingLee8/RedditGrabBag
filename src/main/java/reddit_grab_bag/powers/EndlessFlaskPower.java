package reddit_grab_bag.powers;

import com.megacrit.cardcrawl.actions.unique.BouncingFlaskAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class EndlessFlaskPower extends BasePower{

    public static final String POWER_ID = makeID("EndlessFlaskPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;

    public EndlessFlaskPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    public void onManualDiscard (){
        this.flash();
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        this.addToBot(new BouncingFlaskAction(randomMonster, this.amount, 1));

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
