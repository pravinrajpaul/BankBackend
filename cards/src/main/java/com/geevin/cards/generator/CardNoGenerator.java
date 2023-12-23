package com.geevin.cards.generator;

import com.geevin.cards.util.Util;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.Random;

public class CardNoGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        Random rnd = new Random();
        String one = Util.leftPadNumber(rnd.nextInt(9999));
        String two = Util.leftPadNumber(rnd.nextInt(9999));
        String three = Util.leftPadNumber(rnd.nextInt(9999));
        String four = Util.leftPadNumber(rnd.nextInt(9999));
        return one + two + three + four;
    }

}
