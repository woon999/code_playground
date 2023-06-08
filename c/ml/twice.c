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
    return (float)rand() / (float)RAND_MAX;
}

// https://acsicorp.com/wp-content/uploads/2020/04/comparision.png
// neruals - x1, x2, x3, ...
// weights - w1, w2, w3, ...
// y = x1*w1 + x2*w2 + x3*w3 + ...
float cost(float w, float b)
{
    // cost 평균 제곱 오차(MSE) - result 크면 나쁨, 0으로 수렴할수록 좋음 
    float result = 0.0f;
    for(size_t i=0; i<train_count; ++i){
        float x = train[i][0];
        float y = x*w + b;

        float diff = y - train[i][1];
        result += diff*diff;
    }

    return result/train_count;
}

int main(void)
{
    srand(time(NULL));
    float w = rand_float()*10.0f;
    float b = rand_float()*5.0f;

    // forward finite difference method derivation 과정
    float eps = 1e-3;
    float rate = 1e-3;
    for(size_t i=0; i<10000; ++i){
        float c = cost(w, b);
        float dw = (cost(w + eps, b) - c)/eps;
        float db = (cost(w, b + eps) - c)/eps;
        w -= rate*dw; 
        b -= rate*db; 
    }
    // printf("w: %f, b: %f, cost: %f \n", w, b, cost(w, b));
    
    return 0;
}