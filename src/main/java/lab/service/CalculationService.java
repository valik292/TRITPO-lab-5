package lab.service;

import lab.counter.RequestCounterThread;
import lab.exeption.ParamsDoesNotExistException;
import lab.model.Calculation;
import lab.model.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CalculationService {
    private static final Logger logger = LogManager.getLogger(CalculationService.class);

    @Autowired
    private ParamsHash hashMap;

    public Calculation calculateSpeedAfterCollision(Parameters parameters) {
        new RequestCounterThread(Thread.currentThread().getName()).start();
        double[] weights = parameters.getWeights();
        double[] speeds = parameters.getSpeeds();
        if (hashMap.findByKey(parameters)) {
            logger.info("get hashMap");
            return hashMap.getParameters(parameters);
        } else {
            if (weights[0] <= 0 || weights[1] <= 0 || speeds[0] <= 0 || speeds[1] <= 0) {
                logger.error("Calculating error. Wrong parameters.");
                throw new ParamsDoesNotExistException("Parameters are negative");
            }
            Calculation result = new Calculation((weights[0] * speeds[0] + weights[1] * speeds[1]) / (weights[0] + weights[1]));
            logger.info("Successful calculations of speed.");
            hashMap.addToMap(parameters, result);
            return result;
        }
    }

    public Map<Parameters, Calculation> getCache() {
        return hashMap.getParametersHashMap();
    }

    public static Double calculateMaxSpeed(List<Calculation> list) {
        if (!list.isEmpty()) {
            return list.stream().map(Calculation::getSpeed).mapToDouble(Double::doubleValue).max().getAsDouble();
        } else {
            return null;
        }
    }

    public static Double calculateMinSpeed(List<Calculation> list) {
        if (!list.isEmpty()) {
            return list.stream().map(Calculation::getSpeed).mapToDouble(Double::doubleValue).min().getAsDouble();
        } else {
            return null;
        }
    }

    public static Double calculateSumOfSpeeds(List<Calculation> list) {
        if (!list.isEmpty()) {
            return list.stream().map(Calculation::getSpeed).mapToDouble(Double::doubleValue).sum();
        } else {
            return null;
        }
    }
}