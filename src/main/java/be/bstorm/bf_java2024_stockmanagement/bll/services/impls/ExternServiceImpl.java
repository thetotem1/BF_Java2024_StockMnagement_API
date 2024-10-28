package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ExternService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.ExternRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Extern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExternServiceImpl implements ExternService {

    private final ExternRepository externRepository;

    @Override
    public Extern save(Extern extern) {

        if(externRepository.existsByEmail(extern.getEmail())){
           throw new RuntimeException("Extern with email " + extern.getEmail() + " already exists");
        }
        extern.setId(UUID.randomUUID());
        return externRepository.save(extern);
    }
}
