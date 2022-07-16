import com.game.BlackJackGame;
import com.game.DecisionMakingStrategy;
import com.game.PlayerDecisionMakingStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URISyntaxException;
import java.util.Scanner;

//@RunWith(PowerMockRunner.class)
public class BlackJackGameTest {

    private Scanner scanner;

    private BlackJackGame blackJackGame;

    FileInputStream in ;

    FileOutputStream out;

    InputStreamReader reader;

    OutputStreamWriter writer;

    @Before
    public void init() throws IOException, URISyntaxException {
        File file = new File(getClass().getClassLoader().getResource("command.txt").toURI());
        in = new FileInputStream(file);
        reader = new InputStreamReader(in , "UTF-8");

        out = new FileOutputStream(file);
        writer = new OutputStreamWriter(out, "UTF-8");
        System.setIn(in);
        blackJackGame = new BlackJackGame(1);
        
    }

    @Test
    public void test_blackjackgame_with_hit_decision() throws IOException {
        writer.write("hit");
        writer.flush();

        blackJackGame.startGame();

        Assert.assertTrue( blackJackGame.getGameStatus().size()==1);
    }


    @Test
    public void test_blackjackgame_with_stand_decision() throws IOException {
        writer.write("stand");
        writer.flush();
        blackJackGame.startGame();

        Assert.assertTrue( blackJackGame.getGameStatus().size()==1);
    }
}
