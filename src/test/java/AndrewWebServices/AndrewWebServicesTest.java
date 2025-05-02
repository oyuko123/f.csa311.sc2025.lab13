package AndrewWebServices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AndrewWebServicesTest {
    Database database;
    RecSys recommender;
    PromoService promoService;
    AndrewWebServices andrewWebService;

    @Before
    public void setUp() {
        database = new InMemoryDatabase(); 
        recommender = mock(RecSys.class);
        promoService = mock(PromoService.class);    

        andrewWebService = new AndrewWebServices(database, recommender, promoService);
    }

    @Test
    public void testLogIn() {
        assertTrue(andrewWebService.logIn("Scotty", 17214));
    }

    @Test
    public void testGetRecommendation() {

        when(recommender.getRecommendation("Scotty")).thenReturn("Animal House");

        assertEquals("Animal House", andrewWebService.getRecommendation("Scotty"));
    }

    @Test
    public void testSendEmail() {
        andrewWebService.sendPromoEmail("test@example.com");
        verify(promoService).mailTo("test@example.com");
    }

    @Test
    public void testNoSendEmail() {
        andrewWebService.logIn("Scotty", 17214);
        verify(promoService, never()).mailTo(anyString());

    }
}
