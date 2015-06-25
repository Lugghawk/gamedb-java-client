import com.gamesdb.client.service.GamesDbServiceImpl;
import org.junit.Test;

public class GamesDbServiceImplTest {

	@Test
	public void testService() {
		GamesDbServiceImpl impl = new GamesDbServiceImpl();
		impl.getPlatformList();

	}
}
