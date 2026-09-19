#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define epoca 3000
#define K 0.3f

float EntNt(float, float, float);
float InitNt(float, float);
float sigmoide(float);
void pesos_initNt();

float Pesos[2];
float bias = 0.5f;

float Pesos2[2];
float bias2 = 0.5f;

float Pesos3[2];
float bias3 = 0.5f;

float Error;

float EntNt(float x0, float x1, float target)
{
    float net = Pesos[0] * x0 + Pesos[1] * x1 - bias;
    net = sigmoide(net);
    float out = net;

    float net2 = Pesos2[0] * x0 + Pesos2[1] * x1 - bias2;
    net2 = sigmoide(net2);
    float out2 = net2;

    float net3 = Pesos3[0] * out + Pesos3[1] * out2 - bias3;
    net3 = sigmoide(net3);
    float out3 = net3;

    Error = target - out3;

    float errorSalida = Error * out3 * (1 - out3);
    float errorN1 = errorSalida * Pesos3[0] * out * (1 - out);
    float errorN2 = errorSalida * Pesos3[1] * out2 * (1 - out2);

    float delta3[2];
    delta3[0] = K * errorSalida * out;
    delta3[1] = K * errorSalida * out2;
    Pesos3[0] += delta3[0];
    Pesos3[1] += delta3[1];
    bias3 -= K * errorSalida;

    float delta[2];
    delta[0] = K * errorN1 * x0;
    delta[1] = K * errorN1 * x1;
    Pesos[0] += delta[0];
    Pesos[1] += delta[1];
    bias -= K * errorN1;

    float delta2[2];
    delta2[0] = K * errorN2 * x0;
    delta2[1] = K * errorN2 * x1;
    Pesos2[0] += delta2[0];
    Pesos2[1] += delta2[1];
    bias2 -= K * errorN2;

    return out3;
}

float InitNt(float x0, float x1)
{
    float net = Pesos[0] * x0 + Pesos[1] * x1 - bias;
    float out = sigmoide(net);

    float net2 = Pesos2[0] * x0 + Pesos2[1] * x1 - bias2;
    float out2 = sigmoide(net2);

    float net3 = Pesos3[0] * out + Pesos3[1] * out2 - bias3;
    float out3 = sigmoide(net3);

    return out3;
}

void pesos_initNt(void)
{
    int i;
    for (i = 0; i < 2; i++) Pesos[i] = (float)rand() / RAND_MAX;
    for (i = 0; i < 2; i++) Pesos2[i] = (float)rand() / RAND_MAX;
    for (i = 0; i < 2; i++) Pesos3[i] = (float)rand() / RAND_MAX;
}

float sigmoide(float s)
{
    return 1.0f / (1.0f + expf(-s));
}

int main()
{
    int i = 0;
    float apr;

    pesos_initNt();

    while (i < epoca)
    {
        apr = EntNt(1, 1, 0);
        apr = EntNt(1, 0, 1);
        apr = EntNt(0, 1, 1);
        apr = EntNt(0, 0, 0);
        i++;
    }

    apr = InitNt(0, 0);
    printf("0 XOR 0 = %f -> %d\n", apr, apr >= 0.5f ? 1 : 0);

    apr = InitNt(0, 1);
    printf("0 XOR 1 = %f -> %d\n", apr, apr >= 0.5f ? 1 : 0);

    apr = InitNt(1, 0);
    printf("1 XOR 0 = %f -> %d\n", apr, apr >= 0.5f ? 1 : 0);

    apr = InitNt(1, 1);
    printf("1 XOR 1 = %f -> %d\n", apr, apr >= 0.5f ? 1 : 0);

    return 0;
}