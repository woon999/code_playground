struct real_data
{
    short age;
    char name[16];
};
struct message
{
    long msg_type;
    struct real_data data;
};