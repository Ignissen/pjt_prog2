template<class T>
custom_allocator<T>::custom_allocator() noexcept //This is here just for the sake of being implemented. It's not really needed for this project.
{

}

template<class T>
T* custom_allocator<T>::allocate(std::size_t n)
{
    return static_cast<T*>(::operator new (n*sizeof(T)));
}

template<class T>
void custom_allocator<T>::deallocate(T* p, std::size_t) noexcept
{
    ::operator delete(p);
}