#include <stdio.h>
#include <stdlib.h>
#include <time.h>

float train[][2] = {
    {0, 0},
    {1, 2},
    {2, 4},
    {3, 6},
    {4, 8},
};

#define train_count (sizeof(train)/sizeof(train[0]))

// 100 Trillion => GPT-4 
// 1 => me ㅎㅎ
float rand_float(void)
{
    srand(time(0));
    printf("rand: %f, max: %f \n", (float)rand(), (float)RAND_MAX);
    return (float)rand() / (float)RAND_MAX;
}

// cost 평균 제곱 오차(MSE) 
//  - result 크면 나쁨, 0으로 수렴할수록 좋음 
float cost(float w, int flag)
{
    float result = 0.0f;
    for(size_t i=0; i<train_count; ++i){
        float x = train[i][0];
        float y = x*w;

        float diff = y - train[i][1];
        result += diff*diff;
        if(flag == 1){
            printf("actual: %f, expected: %f\n",y,train[i][1]);
        }
    }

    return result/train_count;
}

int main(void)
{
    float w = rand_float()*10.0f;
    
    // 유한 차분은 함수의 도함수를 근사화하는 데 사용되는 수치 방법이다. https://en.wikipedia.org/wiki/Finite_difference
    // forward finite difference method derivation 과정
    //  0) eps, rate는 임의로 값을 조정한다.
    //  1) f'(w) = f(w+h) - f(w)/h 
    //  2) w = w - w'
    //  3) 1)~2) 과정을 돌려 근사치로 도달한다.
    float eps = 1e-3;
    float rate = 1e-3;
    printf("w: %f, cost: %f \n", w, cost(w, 1));
    for(size_t i=0; i<10000; ++i){
        float dcost = (cost(w + eps, 0) - cost(w, 0))/eps;
        w -= rate*dcost; 
    }
    printf("w: %f, cost: %f \n", w, cost(w, 1));
    
    return 0;
}