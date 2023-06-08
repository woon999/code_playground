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

int main(void){
    float w = rand_float()*10.0f;

    printf("w: %f \n", w);

    float result = 0.0f;
    for(size_t i=0; i<train_count; ++i){
        float x = train[i][0];
        float y = x*w;

        float diff = y - train[i][1];
        result += diff*diff;
        printf("actual: %f, expected: %f\n",y,train[i][1]);
    }

    // result 크면 나쁨, 0으로 수렴할수록 좋음 
    result /= train_count;

    printf("%f\n", result);

    return 0;
}