package br.com.studo.web.resource;

import br.com.studo.domain.dto.DashbordDTO;
import br.com.studo.service.DashbordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashbord")
public class DassbordResource {

    @Autowired
    private DashbordService dashbordService;

    @GetMapping
    public ResponseEntity<DashbordDTO> dashbord() {
        DashbordDTO dashbord = dashbordService.montarDashBord();
        return dashbord != null ? ResponseEntity.status(HttpStatus.OK).body(dashbord) : ResponseEntity.status(HttpStatus.OK).body(new DashbordDTO());
    }
}
