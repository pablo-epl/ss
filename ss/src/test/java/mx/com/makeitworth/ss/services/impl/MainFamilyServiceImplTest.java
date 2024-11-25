package mx.com.makeitworth.ss.services.impl;

import mx.com.makeitworth.ss.dtos.request.AddMainFamilyRequest;
import mx.com.makeitworth.ss.dtos.response.MainFamilyResponse;
import mx.com.makeitworth.ss.entities.MainFamily;
import mx.com.makeitworth.ss.repositories.MainFamilyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MainFamilyServiceImplTest {

    @Mock
    private MainFamilyRepository mainFamilyRepository;

    @InjectMocks
    private MainFamilyServiceImpl mainFamilyService;

    @Test
    public void addMainFamily_ShouldReturnResponse_WhenMainFamilyIsSaved() {
        // Arrange
        AddMainFamilyRequest request = new AddMainFamilyRequest();
        request.setName("La Colmena");

        MainFamily mainFamily = new MainFamily();
        mainFamily.setId(1L);
        mainFamily.setName("La Colmena");

        Mockito.when(mainFamilyRepository.save(Mockito.any(MainFamily.class))).thenReturn(mainFamily);

        // Act
        MainFamilyResponse response = mainFamilyService.addMainFamily(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("La Colmena", response.getName());
    }
}
