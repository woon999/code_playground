# ML in C

![neural network](https://acsicorp.com/wp-content/uploads/2020/04/comparision.png)

## 1) cost 측정 - 평균 제곱 오차(MSE)
훈련된 모델의 값을 평가하려면 train 데이터 세트의 예측 값과 실제 값 사이의 평균 제곱 오차(MSE)를 계산할 수 있다. MSE가 낮을수록 모델 성능이 좋다.
- MSE는 result 변수의 차이 제곱을 누적한 다음 데이터 세트의 데이터 포인트 수(train_count)로 나누어 계산한다.
- MSE는 모델의 정확도 측정값으로 해석할 수 있으며 값이 낮을수록 주어진 데이터 세트에서 더 나은 예측 성능을 나타낸다.
- 예를 들어 계산된 MSE가 0이면 예측 값이 'train' 데이터 세트의 실제 값과 완벽하게 일치한다는 의미이다. 반대로 MSE가 클수록 예측 값과 실제 값 사이의 불일치가 높음을 나타내며 모델 성능이 좋지 않음을 나타낸다.


</br>

## 2) model training - Finite difference
유한 차분은 함수의 도함수를 근사화하는 데 사용되는 수치 방법 
https://en.wikipedia.org/wiki/Finite_difference

### forward finite difference method derivation 과정
0. eps, rate는 임의로 값을 조정한다.
1. w' = f(w+h) - f(w)/h 
2. w = w - w'
3. 1)~2) 과정을 돌려 근사치로 도달한다.

</br>

## result - twice

``` 
------------ 초기 데이터 --------------
actual: 4.486449, expected: 0.000000
actual: 13.459348, expected: 2.000000
actual: 22.432247, expected: 4.000000
actual: 31.405144, expected: 6.000000
actual: 40.378044, expected: 8.000000
w: 8.972898, b: 4.486449, cost: 436.990326

---------- 10000번 트레이닝 ------------
actual: 0.006138, expected: 0.000000
actual: 2.003509, expected: 2.000000
actual: 4.000879, expected: 4.000000
actual: 5.998250, expected: 6.000000
actual: 7.995620, expected: 8.000000
w: 1.997370, b: 0.006138, cost: 0.000015
```

## result - gates
```
------------ 초기 데이터 --------------
w1 = 9.168773, w2 = 9.566946, b = 0.828650, c = 0.121128

---------- 10000번 트레이닝 ------------
w1 = 9.168773, w2 = 9.566946, b = -3.392821, c = 0.000268
```

gnuplot > `plot "cost.txt" with lines`

</br>

---
refs 
- https://github.com/tsoding/perceptron