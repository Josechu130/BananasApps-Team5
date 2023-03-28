package es.netmind.banana_invoices.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.netmind.banana_invoices.models.Propietario;
import es.netmind.banana_invoices.models.Recibo;
import es.netmind.banana_invoices.models.User;
import es.netmind.banana_invoices.persistence.IPropietarioRepo;
import es.netmind.banana_invoices.persistence.IReciboRepo;
import es.netmind.banana_invoices.persistence.UserRepository;
import es.netmind.banana_invoices.services.IInventario;

@RestController
@RequestMapping("")
public class UserServiceController {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    IInventario iInventario;
    
    @Autowired
    IPropietarioRepo propRep;
    
    @Autowired
    IReciboRepo recRepo;

    @GetMapping(value = "/recibos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> products = userRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/recibos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRecibo(@RequestBody @Valid Recibo newRecibo) {
//      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String enc_password = newUser.getPassword(); //passwordEncoder.encode(newUser.getPassword());
//      newUser.setId(null);
//      newUser.setPassword(enc_password);
//      userRepository.save(newUser);
    	
    	Recibo rec = new Recibo();
    	
    	rec.setFecha_vencimiento(newRecibo.getFecha_vencimiento());
    	rec.setNombre_producto(newRecibo.getNombre_producto());
    	rec.setCantidad(newRecibo.getCantidad());
    	rec.setPrecio_unitario(newRecibo.getPrecio_unitario());
    	rec.setBase_imponible(newRecibo.getBase_imponible());
    	rec.setImpuestos(newRecibo.getImpuestos());
    	rec.setTotal(newRecibo.getTotal());
    	rec.setEstado(false);
    	rec.setValido(false);
    	
    	iInventario.saveRecibo(rec);
        return new ResponseEntity<>(rec, HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/propietarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPropietarios(@RequestBody @Valid Propietario newPropietario) {
//      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String enc_password = newUser.getPassword(); //passwordEncoder.encode(newUser.getPassword());
//      newUser.setId(null);
//      newUser.setPassword(enc_password);
//      userRepository.save(newUser);
        
        Propietario prop = new Propietario();
        
        prop.setNombre(newPropietario.getNombre());
        prop.setEmail(newPropietario.getEmail());
        prop.setTelefono(newPropietario.getTelefono());
        prop.setSeccion(newPropietario.getSeccion());
        
        iInventario.savePropietario(prop);

        return new ResponseEntity<>(prop, HttpStatus.CREATED);
    }
    
    @PatchMapping(value = "/recibos/{reciboId}/{propietarioId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRecibos(@PathVariable(name = "reciboId") Long reciboId, @PathVariable(name = "propietarioId") Long propietarioId) {
//      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String enc_password = newUser.getPassword(); //passwordEncoder.encode(newUser.getPassword());
//      newUser.setId(null);
//      newUser.setPassword(enc_password);
//      userRepository.save(newUser);
    	
    	Recibo rec = recRepo.findById(reciboId);
        Propietario prop = propRep.findById(propietarioId);
        
        rec.setPropietario(prop);
        
        iInventario.saveRecibo(rec);
        
        return new ResponseEntity<>(rec, HttpStatus.ACCEPTED);
    }
    
    @PatchMapping(value = "/validarRecibos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateValidoRecibos(@PathVariable(name = "reciboId") Long reciboId) {
//      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String enc_password = newUser.getPassword(); //passwordEncoder.encode(newUser.getPassword());
//      newUser.setId(null);
//      newUser.setPassword(enc_password);
//      userRepository.save(newUser);
    	
    	List<Recibo> rec = iInventario.findAllRecibos();
    	
    	
    			
        return new ResponseEntity<>(rec, HttpStatus.ACCEPTED);
    }
    
    @PatchMapping(value = "/recibos/{reciboId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePagadoRecibos(@PathVariable(name = "reciboId") Long reciboId) {
//      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String enc_password = newUser.getPassword(); //passwordEncoder.encode(newUser.getPassword());
//      newUser.setId(null);
//      newUser.setPassword(enc_password);
//      userRepository.save(newUser);
    	boolean response;
    	
    	Recibo rec = recRepo.findById(reciboId);
        
        if(!rec.isEstado()) {
        	rec.setEstado(true);
        	recRepo.save(rec);
        	response = true;
        }else {
        	response = false;
        }
        
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}