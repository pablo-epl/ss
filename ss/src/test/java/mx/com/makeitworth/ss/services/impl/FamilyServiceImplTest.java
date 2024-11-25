package mx.com.makeitworth.ss.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import mx.com.makeitworth.ss.dtos.request.AddFamilyRequest;
import mx.com.makeitworth.ss.dtos.response.FamilyResponse;
import mx.com.makeitworth.ss.entities.Family;
import mx.com.makeitworth.ss.entities.MainFamily;
import mx.com.makeitworth.ss.repositories.FamilyRepository;
import mx.com.makeitworth.ss.repositories.MainFamilyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FamilyServiceImplTest {

    @Mock
    private FamilyRepository familyRepository;

    @Mock
    private MainFamilyRepository mainFamilyRepository;

    @InjectMocks
    private FamilyServiceImpl familyService;

    @Test
    public void addFamily_ShouldReturnResponse_WhenMainFamilyExists() {
        // Arrange
        AddFamilyRequest request = new AddFamilyRequest();
        request.setName("Kokos");
        request.setMainFamilyId(1L);

        MainFamily mainFamily = new MainFamily();
        mainFamily.setId(1L);
        mainFamily.setName("La Colmena");

        Family savedFamily = new Family();
        savedFamily.setId(2L);
        savedFamily.setName("Kokos");
        savedFamily.setMainFamily(mainFamily);

        when(mainFamilyRepository.findById(1L)).thenReturn(java.util.Optional.of(mainFamily));
        when(familyRepository.save(any(Family.class))).thenReturn(savedFamily);

        // Act
        FamilyResponse response = familyService.addFamily(request);

        // Assert
        assertNotNull(response);
        assertEquals(2L, response.getId());
        assertEquals("Kokos", response.getName());
        assertEquals(1L, response.getMainFamilyId());

        verify(mainFamilyRepository, times(1)).findById(1L);
        verify(familyRepository, times(1)).save(any(Family.class));
    }

    @Test
    public void addFamily_ShouldThrowException_WhenMainFamilyDoesNotExist() {
        // Arrange
        AddFamilyRequest request = new AddFamilyRequest();
        request.setName("Kokos");
        request.setMainFamilyId(999L);

        when(mainFamilyRepository.findById(999L)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            familyService.addFamily(request);
        });

        assertEquals("Main family with ID 999 does not exist.", exception.getMessage());
        verify(mainFamilyRepository, times(1)).findById(999L);
        verifyNoInteractions(familyRepository);
    }
}
