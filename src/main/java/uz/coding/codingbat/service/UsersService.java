package uz.coding.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.coding.codingbat.entity.Users;
import uz.coding.codingbat.payload.ApiResponse;
import uz.coding.codingbat.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository userRepository;
    @Autowired
    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Userlarni bazaga qo'shadigan
     * @param userDto cilentdan keladigan json filni pars qilib beradi
     * @return qo'shilgan qo'zshilmagan xabarini beradi
     */
    public ApiResponse addUser(Users userDto) {
        if (userRepository.existsAllByEmail(userDto.getEmail()))
            return new ApiResponse("Already exist email",false);
        Users users = new Users();
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());
        users.setGender(userDto.getGender());
        userRepository.save(users);
        return new ApiResponse("Succsessfully added",true);
    }

    public ApiResponse updateUser(Integer id,Users userDto){
        Optional<Users> optionalUsers = userRepository.findById(id);
        if (!optionalUsers.isPresent())
            return new ApiResponse("Users not found",false);
        if (userRepository.existsAllByEmailAndIdNot(userDto.getEmail(),id))
            return new ApiResponse("Already users email",false);
        Users users = optionalUsers.get();
        users.setEmail(userDto.getEmail());
        users.setGender(userDto.getGender());
        users.setPassword(userDto.getPassword());
        userRepository.save(users);
        return new ApiResponse("Successfully update",true);
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users getById(Integer id){
        Optional<Users> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public ApiResponse deleteUser(Integer id){
        if (!userRepository.existsById(id))
            return new ApiResponse("bunday idli user mavjud emas", false);
        userRepository.deleteById(id);
        return new ApiResponse("Muvaffaqiyatli o'chirildi", true);
    }
}
