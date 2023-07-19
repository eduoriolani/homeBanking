package com.mindhub.homeBanking;

import com.mindhub.homeBanking.utils.AccountUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class AccountUtilsTest {

    @Test
    public void accountNumIsCreated(){
        String accountNum = AccountUtils.getRandomNum();
        assertThat(accountNum, is(not(emptyOrNullString())));
    }
}
