package mx.com.makeitworth.ss.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import mx.com.makeitworth.ss.dtos.request.AddFamilyMemberRequest;
import mx.com.makeitworth.ss.dtos.response.AddFamilyMemberResponse;
import mx.com.makeitworth.ss.entities.Family;
import mx.com.makeitworth.ss.entities.FamilyMember;
import mx.com.makeitworth.ss.repositories.FamilyMemberRepository;
import mx.com.makeitworth.ss.repositories.FamilyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FamilyMemberServiceImplTest {

    @Mock
    private FamilyMemberRepository familyMemberRepository;

    @Mock
    private FamilyRepository familyRepository;

    @InjectMocks
    private FamilyMemberServiceImpl familyMemberService;

    @Test
    public void addFamilyMember_ShouldReturnResponse_WhenFamilyExists() {
        // Arrange
        AddFamilyMemberRequest request = new AddFamilyMemberRequest();
        request.setName("Nube");
        request.setEmail("nube@example.com");
        request.setFamilyId(1L);

        Family family = new Family();
        family.setId(1L);
        family.setName("Kokos");

        FamilyMember savedFamilyMember = new FamilyMember();
        savedFamilyMember.setId(2L);
        savedFamilyMember.setName("Nube");
        savedFamilyMember.setEmail("nube@example.com");
        savedFamilyMember.setFamily(family);

        when(familyRepository.findById(1L)).thenReturn(java.util.Optional.of(family));
        when(familyMemberRepository.save(any(FamilyMember.class))).thenReturn(savedFamilyMember);

        // Act
        AddFamilyMemberResponse response = familyMemberService.addFamilyMember(request);

        // Assert
        assertNotNull(response);
        assertEquals(2L, response.getId());
        assertEquals("Nube", response.getName());
        assertEquals("nube@example.com", response.getEmail());
        assertEquals(1L, response.getFamilyId());

        verify(familyRepository, times(1)).findById(1L);
        verify(familyMemberRepository, times(1)).save(any(FamilyMember.class));
    }

    @Test
    public void addFamilyMember_ShouldThrowException_WhenFamilyDoesNotExist() {
        // Arrange
        AddFamilyMemberRequest request = new AddFamilyMemberRequest();
        request.setName("Rio");
        request.setEmail("rio@example.com");
        request.setFamilyId(999L);

        when(familyRepository.findById(999L)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            familyMemberService.addFamilyMember(request);
        });

        assertEquals("Family with ID 999 does not exist.", exception.getMessage());
        verify(familyRepository, times(1)).findById(999L);
        verifyNoInteractions(familyMemberRepository);
    }
}
