#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

typedef struct{
    float or_w1;
    float or_w2;
    float or_b;

    float and_w1;
    float and_w2;
    float and_b;

    float nand_w1;
    float nand_w2;
    float nand_b;
} Xor;

float sigmoidf(float x){
    return  1.f / (1.f + expf(-x));
}

float forward(Xor m, float x1, float x2){
    float a = sigmoidf(m.or_w1*x1 + m.or_w2*x2 + m.or_b);
    float b = sigmoidf(m.nand_w1*x1 + m.nand_w2*x2 + m.nand_b);
    return sigmoidf(a*m.and_w1 + b*m.and_w2 + m.and_b);
}

typedef float sample[3];
sample xor_train[] = {
    {0, 0, 0},
    {1, 0, 1},
    {0, 1, 1},
    {1, 1, 0},
};

sample *train = xor_train;
size_t train_count = 4;

float cost(Xor m){
    // cost 평균 제곱 오차(MSE) - result 크면 나쁨, 0으로 수렴할수록 좋음 
    float result = 0.0f;
    for(size_t i=0; i<train_count; ++i){
        float x1 = train[i][0];
        float x2 = train[i][1];
        float y = forward(m, x1, x2);
        float d = y - train[i][2];
        result += d*d;
    }

    return result/train_count;
}

float rand_float(void){
    return (float)rand() / (float)RAND_MAX;
}

Xor rand_xor()
{
    Xor m;
    m.or_w1 = rand_float();
    m.or_w2 = rand_float();
    m.or_b = rand_float();
    m.and_w1 = rand_float();
    m.and_w2 = rand_float();
    m.and_b = rand_float();
    m.nand_w1 = rand_float();
    m.nand_w2 = rand_float();
    m.nand_b = rand_float();

    return m;
}

void print_xor(Xor m){
    printf("or_w1 = %f \n", m.or_w1);
    printf("or_w2 = %f \n", m.or_w2);
    printf("or_b = %f \n", m.or_b);
    printf("and_w1 = %f \n", m.and_w1);
    printf("and_w2 = %f \n", m.and_w2);
    printf("and_b = %f \n", m.and_b);
    printf("nand_w1 = %f \n", m.nand_w1);
    printf("nand_w2 = %f \n", m.nand_w2);
    printf("nand_b = %f \n", m.nand_b);
}

Xor finite_diff(Xor m){

    Xor g;
    float c = cost(m);
    float saved;
    float eps = 1e-2;

    saved = m.or_w1;
    m.or_w1 += eps;
    g.or_w1 = (cost(m) -c)/eps;
    m.or_w1 = saved;

    saved = m.or_w1;
    m.or_w1 += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    saved = m.or_w2;
    m.or_w2 += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    saved = m.or_b;
    m.or_b += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    saved = m.and_w1;
    m.and_w1 += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    saved = m.and_w2;
    m.and_w2 += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    saved = m.and_b;
    m.and_b += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    saved = m.nand_w1;
    m.nand_w1 += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    saved = m.nand_w2;
    m.nand_w2 += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    saved = m.nand_b;
    m.nand_b += eps;
    g.or_w1 = (cost(m) - c)/eps;
    m.or_w1 = saved;
    
    return m;
}

int main(void){
    srand(time(NULL));
    rand();

    Xor m = rand_xor();
    print_xor(m);

    printf("------------------------------------\n");
    print_xor(finite_diff(m));
    printf("cost = %f \n", cost(m));

    return 0;
}
