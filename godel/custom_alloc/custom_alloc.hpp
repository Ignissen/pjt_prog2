//https://howardhinnant.github.io/allocator_boilerplate.html

#include <cstddef>
//The definition of the custom_allocator class
template<class T> //T will be the type of needed allocation
class custom_allocator
{
public:
    using value_type = T;
    custom_allocator() noexcept;
    value_type* allocate(std::size_t n);
    void deallocate(value_type* p, std::size_t) noexcept;
    
private:
protected:
};
#include "custom_alloc.tcc" //To include the template implementations. Saw this solution on StackOverflow (https://stackoverflow.com/a/10632266) and it seems like a good idea to use it.


template <class T, class U>
bool operator==(custom_allocator<T> const&, custom_allocator<U> const&) noexcept
{
    return true;
}

template <class T, class U>
bool operator!=(custom_allocator<T> const& x, custom_allocator<U> const& y) noexcept
{
    return !(x == y);
}
