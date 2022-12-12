package lab.controller;

import lab.exeption.ParamsDoesNotExistException;
import lab.model.Calculation;
import lab.model.Parameters;
import lab.model.ResultDto;
import lab.model.WrapperObject;
import lab.service.CalculationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class CalculationController {
    private static final Logger logger = LogManager.getLogger(CalculationController.class);

    @Autowired
    private final CalculationService calculationService;

    public CalculationController(CalculationService service) {
        this.calculationService = service;
    }

    @GetMapping("/calculate")
    public Calculation calculateSpeed(@RequestParam(value = "weight1") double w1,
                                      @RequestParam(value = "speed1") double s1,
                                      @RequestParam(value = "weight2") double w2,
                                      @RequestParam(value = "speed2") double s2)
                        throws ParamsDoesNotExistException {

        logger.info("Action with getSpeeds mapping.");
        return calculationService.calculateSpeedAfterCollision(new Parameters(new double[]{w1, w2}, new double[]{s1, s2}));
    }

    @GetMapping("/cache")
    public Map<Parameters, Calculation> getCache() {
        logger.info("Successfully got speeds from Map");
        return calculationService.getCache();
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> bulkInclusion(@Valid @RequestBody WrapperObject wrapperObject) {
        ResultDto dto = new ResultDto();
        List<Parameters> bodyList = wrapperObject.getObjects();
        if (bodyList.isEmpty()) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        List<Calculation> numberOfParameters = new LinkedList<>();
        bodyList.forEach((element) -> {
            try {
                numberOfParameters.add(calculationService.calculateSpeedAfterCollision(element));
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                System.exit(2);
            } catch (IllegalArgumentException iae) {
                iae.printStackTrace();
                System.exit(3);
            } catch (IllegalStateException ise) {
                ise.printStackTrace();
                System.exit(4);
            }
        });
        dto.setCalculations(numberOfParameters);
        dto.setMaxSpeed(CalculationService.calculateMaxSpeed(numberOfParameters));
        dto.setMinSpeed(CalculationService.calculateMinSpeed(numberOfParameters));
        dto.setSumOfSpeeds(CalculationService.calculateSumOfSpeeds(numberOfParameters));
        return new ResponseEntity<>(numberOfParameters, HttpStatus.OK);
    }
}


