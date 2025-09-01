package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.util.CardStats;

public class SpectralBlade extends BaseCard{

    public static final String ID = makeID(SpectralBlade.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.RED, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 1);

    public SpectralBlade() {
        super(ID, info);
        this.baseDamage = 12;
        this.setEthereal(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpectralBlade();
    }
}
