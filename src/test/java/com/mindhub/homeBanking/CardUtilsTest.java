package com.mindhub.homeBanking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.mindhub.homeBanking.utils.CardUtils;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CardUtilsTest {

    @Test
    public void cardNumberIsCreated(){
        String cardNumber = CardUtils.getCardNumber();
        assertThat(cardNumber,is(not(emptyOrNullString())));
    }
    @Test
    public void cardCvvIsCreated(){
        String cardCvv = CardUtils.getCvvNumber();
        assertThat(cardCvv,is(not(emptyOrNullString())));
    }
}
